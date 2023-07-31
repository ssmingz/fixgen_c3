class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    OS.PtSetResource(handle, Pt_ARG_MULTITEXT_TOP_LINE, index + 1, 0);
  }
}
