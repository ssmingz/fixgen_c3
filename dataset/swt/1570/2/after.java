class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    OS.PtSetResource(handle, Pt_ARG_MAXIMUM, value - 1, 0);
  }
}
