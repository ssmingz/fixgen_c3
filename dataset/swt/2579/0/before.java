class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    int[] args = new int[] {OS.Pt_ARG_MINIMUM, value, 0};
    OS.PtSetResources(handle, args.length / 3, args);
  }
}
