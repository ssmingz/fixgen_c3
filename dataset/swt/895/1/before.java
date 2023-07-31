class PlaceHold {
  public int getMinimum() {
    checkWidget();
    return OS.GetControl32BitMinimum(handle);
  }
}
