class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if ((!checkRTF(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int pValue = OS.XtMalloc(buffer.length);
    if (pValue == 0) {
      return;
    }
    OS.memmove(pValue, buffer, buffer.length);
    transferData.length = buffer.length - 1;
    transferData.format = 8;
    transferData.pValue = pValue;
    transferData.result = 1;
  }
}
