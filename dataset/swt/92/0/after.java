class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    Display display = getDisplay();
    int clazz = display.PtContainer;
    int parentHandle = parent.handle;
    int cursor =
        ((style & SWT.HORIZONTAL) != 0) ? OS.Ph_CURSOR_DRAG_VERTICAL : OS.Ph_CURSOR_DRAG_HORIZONTAL;
    int[] args =
        new int[] {
          OS.Pt_ARG_FLAGS,
          0,
          OS.Pt_GETS_FOCUS,
          OS.Pt_ARG_CURSOR_TYPE,
          cursor,
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
