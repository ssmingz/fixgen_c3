class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if ((!checkText(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    if (transferData.type == COMPOUND_TEXT_ID) {
      int[] encoding = new int[1];
      int[] format = new int[1];
      int[] ctext = new int[1];
      int[] length = new int[1];
      boolean result = OS.gdk_utf8_to_compound_text(buffer, encoding, format, ctext, length);
      if (!result) {
        return;
      }
      transferData.type = encoding[0];
      transferData.format = format[0];
      transferData.length = length[0];
      transferData.pValue = ctext[0];
      transferData.result = 1;
    }
    if (transferData.type == STRING_ID) {
      int pValue = OS.g_malloc(buffer.length);
      if (pValue == 0) {
        return;
      }
      OS.memmove(pValue, buffer, buffer.length);
      transferData.type = STRING_ID;
      transferData.format = 8;
      transferData.length = buffer.length;
      transferData.pValue = pValue;
      transferData.result = 1;
    }
  }
}
