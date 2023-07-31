class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    int maximum = getMaximum();
    if ((0 <= value) && (value < maximum)) {
      OS.PtSetResource(handle, Pt_ARG_MINIMUM, value, 0);
    }
  }
}
