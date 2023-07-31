class PlaceHold {
  public byte[] getBytes() {
    byte[] result = new byte[2];
    putShort(value, result, 0);
    return result;
  }
}
