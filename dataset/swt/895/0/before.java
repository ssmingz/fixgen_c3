class PlaceHold {
  public int getSelection() {
    checkWidget();
    return OS.GetControl32BitValue(handle);
  }
}
