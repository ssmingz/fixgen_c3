class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int clazz = Display.PtProgress;
    int parentHandle = parent.parentingHandle();
    int gaugeFlags = ((style & SWT.INDETERMINATE) != 0) ? OS.Pt_GAUGE_INDETERMINATE : 0;
    int[] args =
        new int[] {
          OS.Pt_ARG_FLAGS,
          0,
          OS.Pt_GETS_FOCUS,
          OS.Pt_ARG_GAUGE_FLAGS,
          gaugeFlags,
          OS.Pt_GAUGE_INDETERMINATE,
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
