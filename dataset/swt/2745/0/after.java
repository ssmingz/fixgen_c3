class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    OS.PtSetResource(handle, Pt_ARG_TOP_ITEM_POS, index + 1, 0);
  }
}
