class PlaceHold {
  public void select(int index) {
    checkWidget();
    if (index < 0) {
      return;
    }
    int[] args = new int[] {OS.Pt_ARG_CBOX_SELECTION_ITEM, index + 1, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
