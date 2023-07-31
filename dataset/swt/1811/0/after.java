class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    if ((!isSupportedType(transferData)) || (transferData.data == null)) {
      return null;
    }
    if (transferData.data.length == 0) {
      return null;
    }
    int count = transferData.data.length;
    String[] fileNames = new String[count];
    for (int i = 0; i < count; i++) {
      byte[] data = transferData.data[i];
      int url = 0;
      if (transferData.type == HFSID) {
        byte[] fsspec = new byte[data.length - 10];
        System.arraycopy(data, 10, fsspec, 0, fsspec.length);
        byte[] fsRef = new byte[80];
        if (OS.FSpMakeFSRef(fsspec, fsRef) != OS.noErr) {
          return null;
        }
        url = OS.CFURLCreateFromFSRef(kCFAllocatorDefault, fsRef);
        if (url == 0) {
          return null;
        }
      }
      if (transferData.type == FURLID) {
        int encoding = OS.kCFStringEncodingUTF8;
        url = OS.CFURLCreateWithBytes(kCFAllocatorDefault, data, data.length, encoding, 0);
        if (url == 0) {
          return null;
        }
      }
      try {
        int path = OS.CFURLCopyFileSystemPath(url, kCFURLPOSIXPathStyle);
        if (path == 0) {
          return null;
        }
        try {
          int length = OS.CFStringGetLength(path);
          if (length == 0) {
            return null;
          }
          char[] buffer = new char[length];
          CFRange range = new CFRange();
          range.length = length;
          OS.CFStringGetCharacters(path, range, buffer);
          fileNames[i] = new String(buffer);
        } finally {
          OS.CFRelease(path);
        }
      } finally {
        OS.CFRelease(url);
      }
    }
    return fileNames;
  }
}
