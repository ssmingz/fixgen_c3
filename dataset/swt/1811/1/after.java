class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!checkURL(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    transferData.result = -1;
    String url = ((String) (object));
    int count = url.length();
    char[] chars = new char[count];
    url.getChars(0, count, chars, 0);
    int cfstring = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, chars, count);
    if (cfstring == 0) {
      return;
    }
    try {
      CFRange range = new CFRange();
      range.length = chars.length;
      int encoding = OS.kCFStringEncodingUTF8;
      int[] size = new int[1];
      int numChars =
          OS.CFStringGetBytes(cfstring, range, encoding, ((byte) ('?')), true, null, 0, size);
      if ((numChars == 0) || (size[0] == 0)) {
        return;
      }
      byte[] buffer = new byte[size[0]];
      numChars =
          OS.CFStringGetBytes(
              cfstring, range, encoding, ((byte) ('?')), true, buffer, size[0], size);
      if (numChars == 0) {
        return;
      }
      transferData.data = new byte[][] {buffer};
      transferData.result = 0;
    } finally {
      OS.CFRelease(cfstring);
    }
  }
}
