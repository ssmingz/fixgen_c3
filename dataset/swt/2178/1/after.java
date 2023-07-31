class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    byte[] buffer = ((byte[]) (super.nativeToJava(transferData)));
    if (buffer == null) {
      return null;
    }
    char[] unicode = Converter.mbcsToWcs(null, buffer);
    return new String(unicode);
  }
}
