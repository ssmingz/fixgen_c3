class PlaceHold {
  public void setSelection(int value) {
    checkWidget();
    OS.PtSetResource(handle, Pt_ARG_NUMERIC_VALUE, value, 0);
  }
}
