class PlaceHold {
  protected void javaToNative(Object object, TransferData transferData) {
    transferData.result = 0;
    if ((!_validate(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    byte[] buffer = ((byte[]) (object));
    if (buffer.length == 0) {
      return;
    }
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
