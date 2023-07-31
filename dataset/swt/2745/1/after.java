class PlaceHold {
  public void select(int index) {
    checkWidget();
    if (index < 0) {
      return;
    }
    OS.PtSetResource(handle, Pt_ARG_CBOX_SELECTION_ITEM, index + 1, 0);
  }
}
