class PlaceHold {
  protected void javaToNative(Object object, TransferData transferData) {
    if (((object == null) || (!(object instanceof byte[]))) || (!isSupportedType(transferData))) {
      transferData.result = 0;
      return;
    }
    byte[] buffer = ((byte[]) (object));
    transferData.pData = OS.malloc(buffer.length);
    OS.memmove(transferData.pData, buffer, buffer.length);
    transferData.length = buffer.length;
    transferData.result = 1;
  }
}
