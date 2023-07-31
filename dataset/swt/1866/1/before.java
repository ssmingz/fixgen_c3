class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    if ((!isSupportedType(transferData)) || (transferData.pValue == 0)) {
      return null;
    }
    byte[] buffer = null;
    if (transferData.type == COMPOUND_TEXT_ID) {
      int[] list = new int[1];
      int count =
          OS.gdk_text_property_to_utf8_list(
              transferData.type,
              transferData.format,
              transferData.pValue,
              transferData.length,
              list);
      if (count == 0) {
        return null;
      }
      int[] ptr = new int[1];
      OS.memmove(ptr, list[0], 4);
      int length = OS.strlen(ptr[0]);
      buffer = new byte[length];
      OS.memmove(buffer, ptr[0], length);
      OS.g_strfreev(list[0]);
    }
    if (transferData.type == STRING_ID) {
      int size = (transferData.format * transferData.length) / 8;
      if (size == 0) {
        return null;
      }
      buffer = new byte[size];
      OS.memmove(buffer, transferData.pValue, size);
    }
    if (buffer == null) {
      return null;
    }
    char[] unicode = Converter.mbcsToWcs(null, buffer);
    String string = new String(unicode);
    int end = string.indexOf('\u0000');
    return end == (-1) ? string : string.substring(0, end);
  }
}
