class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    OS.PtSetResource(handle, Pt_ARG_NUMERIC_MAX, value, 0);
  }
}
