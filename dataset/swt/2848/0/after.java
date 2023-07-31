class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if ((!validate(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    int charCount = string.length();
    char[] chars = new char[charCount + 1];
    string.getChars(0, charCount, chars, 0);
    int byteCount = chars.length * 2;
    int pValue = OS.g_malloc(byteCount);
    if (pValue == 0) {
      return;
    }
    OS.memmove(pValue, chars, byteCount);
    transferData.length = byteCount;
    transferData.format = 8;
    transferData.pValue = pValue;
    transferData.result = 1;
  }
}
