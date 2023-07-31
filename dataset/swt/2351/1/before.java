class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = -1;
    if (((object == null) || (!(object instanceof String[]))) || (!isSupportedType(transferData))) {
      return;
    }
    String[] files = ((String[]) (object));
    if (files.length == 0) {
      return;
    }
    if (transferData.type == URILISTID) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0, length = files.length; i < length; i++) {
        sb.append(URILIST_PREFIX);
        sb.append(files[i]);
        sb.append(URILIST_SEPARATOR);
      }
      String str = sb.toString();
      char[] chars = new char[str.length()];
      str.getChars(0, chars.length, chars, 0);
      byte[] buffer = new byte[chars.length * 2];
      OS.memcpy(buffer, chars, buffer.length);
      transferData.data = new byte[1][];
      transferData.data[0] = buffer;
      transferData.result = 0;
    }
    if (transferData.type == HFSID) {
      byte[][] data = new byte[files.length][];
      for (int i = 0; i < data.length; i++) {
        File file = new File(files[i]);
        boolean isDirectory = file.isDirectory();
        String fileName = files[i];
        char[] chars = new char[fileName.length()];
        fileName.getChars(0, chars.length, chars, 0);
        int cfstring = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, chars, chars.length);
        if (cfstring == 0) {
          return;
        }
        try {
          int url =
              OS.CFURLCreateWithFileSystemPath(
                  kCFAllocatorDefault, cfstring, kCFURLPOSIXPathStyle, isDirectory);
          if (url == 0) {
            return;
          }
          try {
            byte[] fsRef = new byte[80];
            if (!OS.CFURLGetFSRef(url, fsRef)) {
              return;
            }
            byte[] fsSpec = new byte[70];
            if (OS.FSGetCatalogInfo(fsRef, 0, null, null, fsSpec, null) != OS.noErr) {
              return;
            }
            byte[] hfsflavor = new byte[10 + fsSpec.length];
            byte[] finfo = new byte[16];
            OS.FSpGetFInfo(fsSpec, finfo);
            System.arraycopy(finfo, 0, hfsflavor, 0, 10);
            System.arraycopy(fsSpec, 0, hfsflavor, 10, fsSpec.length);
            data[i] = hfsflavor;
          } finally {
            OS.CFRelease(url);
          }
        } finally {
          OS.CFRelease(cfstring);
        }
      }
      transferData.data = data;
      transferData.result = 0;
    }
  }
}
