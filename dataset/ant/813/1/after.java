class PlaceHold {
  public void setSize(long size) {
    if (size < 0) {
      throw new IllegalArgumentException("Size is out of range: " + size);
    }
    this.size = size;
  }
}
