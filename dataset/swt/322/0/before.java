class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if (((object == null) || (!(object instanceof String))) || (!isSupportedType(transferData))) {
      return;
    }
    String string = ((String) (object));
    if (string.length() == 0) {
      return;
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int pValue = OS.g_malloc(buffer.length);
    if (pValue == 0) {
      return;
    }
    OS.memmove(pValue, buffer, buffer.length);
    transferData.length = buffer.length;
    transferData.format = 8;
    transferData.pValue = pValue;
    transferData.result = 1;
  }
}
