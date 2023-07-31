class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    int[] args = new int[] {OS.Pt_ARG_TOP_ITEM_POS, index + 1, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
