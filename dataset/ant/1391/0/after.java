class PlaceHold {
  public byte[] getRawName() {
    if (rawName != null) {
      final byte[] b = new byte[rawName.length];
      System.arraycopy(rawName, 0, b, 0, rawName.length);
      return b;
    }
    return null;
  }
}
