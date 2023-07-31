class PlaceHold {
  protected Object nativeToJava(TransferData transferData) {
    if ((!isSupportedType(transferData)) || (transferData.data == null)) {
      return null;
    }
    if (transferData.data == null) {
      return null;
    }
    NSData data = ((NSData) (transferData.data));
    if (data.length() == 0) {
      return null;
    }
    byte[] bytes = new byte[data.length()];
    data.getBytes(bytes);
    return bytes;
  }
}
