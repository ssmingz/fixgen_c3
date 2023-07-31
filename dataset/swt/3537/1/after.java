class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    OS.PtSetResource(handle, Pt_ARG_NUMERIC_MIN, value, 0);
  }
}
