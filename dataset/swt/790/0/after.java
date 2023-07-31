class PlaceHold {
  void resizeClientArea() {
    int[] args = new int[] {OS.Pt_ARG_WIDTH, 0, 0, OS.Pt_ARG_HEIGHT, 0, 0};
    OS.PtGetResources(scrolledHandle, args.length / 3, args);
    resizeClientArea(args[1], args[4], true);
  }
}
