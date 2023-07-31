class PlaceHold {
  protected void javaToNative(Object object, TransferData transferData) {
    if ((!_validate(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    byte[] buffer = ((byte[]) (object));
    transferData.pData = OS.malloc(buffer.length);
    OS.memmove(transferData.pData, buffer, buffer.length);
    transferData.length = buffer.length;
    transferData.result = 1;
  }
}
