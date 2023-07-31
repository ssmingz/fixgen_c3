class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if (((object == null) || (!(object instanceof String))) || (!isSupportedType(transferData))) {
      return;
    }
    String string = ((String) (object));
    int charCount = string.length();
    if (charCount == 0) {
      return;
    }
    char[] chars = new char[charCount + 1];
    string.getChars(0, charCount, chars, 0);
    int byteCount = chars.length * 2;
    int pValue = OS.XtMalloc(byteCount);
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
