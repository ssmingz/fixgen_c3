class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    Display display = getDisplay();
    int clazz = display.PtSlider;
    int parentHandle = parent.handle;
    int[] args =
        new int[] {
          OS.Pt_ARG_MAXIMUM,
          100,
          0,
          OS.Pt_ARG_PAGE_INCREMENT,
          10,
          0,
          OS.Pt_ARG_SLIDER_SIZE,
          10,
          0,
          OS.Pt_ARG_ORIENTATION,
          (style & SWT.HORIZONTAL) != 0 ? OS.Pt_HORIZONTAL : OS.Pt_VERTICAL,
          0,
          OS.Pt_ARG_RESIZE_FLAGS,
          0,
          OS.Pt_RESIZE_XY_BITS
        };
    handle = OS.PtCreateWidget(clazz, parentHandle, args.length / 3, args);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
