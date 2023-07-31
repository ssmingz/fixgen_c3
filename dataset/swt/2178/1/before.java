class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    byte[] buffer = ((byte[]) (super.nativeToJava(transferData)));
    if (buffer == null) {
      return null;
    }
    return new String(buffer);
  }
}
