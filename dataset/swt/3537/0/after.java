class PlaceHold {
  public void setIncrement(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    OS.PtSetResource(handle, Pt_ARG_NUMERIC_INCREMENT, value, 0);
  }
}
