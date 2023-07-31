class PlaceHold {
  public int getMinimum() {
    checkWidget();
    return OS.GetControl32BitMinimum(handle) & 0x7fffffff;
  }
}
