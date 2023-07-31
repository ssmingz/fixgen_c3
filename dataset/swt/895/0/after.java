class PlaceHold {
  public int getSelection() {
    checkWidget();
    return OS.GetControl32BitValue(handle) & 0x7fffffff;
  }
}
