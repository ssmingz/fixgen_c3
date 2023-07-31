class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int clazz = Display.PtContainer;
    int parentHandle = parent.parentingHandle();
    int[] args = new int[] {OS.Pt_ARG_RESIZE_FLAGS, 0, OS.Pt_RESIZE_XY_BITS};
    handle = OS.PtCreateWidget(clazz, parentHandle, args.length / 3, args);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
