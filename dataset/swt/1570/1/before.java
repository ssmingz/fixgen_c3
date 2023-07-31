class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    int[] args = new int[] {OS.Pt_ARG_MULTITEXT_TOP_LINE, index + 1, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
