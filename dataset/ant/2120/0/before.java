class PlaceHold {
  public byte[] getLocalFileDataExtra() {
    byte[] extra = getExtra();
    return extra != null ? extra : new byte[0];
  }
}
