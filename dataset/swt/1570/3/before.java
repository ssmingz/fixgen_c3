class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    int[] args = new int[] {OS.Pt_ARG_MAXIMUM, value - 1, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
