class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!checkText(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    char[] chars = new char[string.length()];
    string.getChars(0, chars.length, chars, 0);
    transferData.result = -1;
    switch (transferData.type) {
      case TEXTID:
        {
          int cfstring = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, chars, chars.length);
          if (cfstring == 0) {
            return;
          }
          byte[] buffer = null;
          try {
            CFRange range = new CFRange();
            range.length = chars.length;
            int encoding = OS.CFStringGetSystemEncoding();
            int[] size = new int[1];
            int numChars =
                OS.CFStringGetBytes(cfstring, range, encoding, ((byte) ('?')), true, null, 0, size);
            if (numChars == 0) {
              return;
            }
            buffer = new byte[size[0]];
            numChars =
                OS.CFStringGetBytes(
                    cfstring, range, encoding, ((byte) ('?')), true, buffer, size[0], size);
            if (numChars == 0) {
              return;
            }
          } finally {
            OS.CFRelease(cfstring);
          }
          transferData.data = new byte[1][];
          transferData.data[0] = buffer;
          transferData.result = OS.noErr;
          break;
        }
      case UTEXTID:
        {
          byte[] buffer = new byte[chars.length * 2];
          OS.memmove(buffer, chars, buffer.length);
          transferData.data = new byte[1][];
          transferData.data[0] = buffer;
          transferData.result = OS.noErr;
          break;
        }
    }
  }
}
