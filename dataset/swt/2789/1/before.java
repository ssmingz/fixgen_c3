class PlaceHold {
  protected void javaToNative(Object object, TransferData transferData) {
    if ((!_validate(object)) && (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    byte[] orig = ((byte[]) (object));
    byte[] buffer = new byte[orig.length];
    System.arraycopy(orig, 0, buffer, 0, orig.length);
    transferData.data = new byte[1][];
    transferData.data[0] = buffer;
    transferData.result = 0;
  }
}
