class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    Display display = getDisplay();
    int parentHandle = parent.parentingHandle();
    int[] args = new int[] {OS.Pt_ARG_RESIZE_FLAGS, 0, OS.Pt_RESIZE_XY_BITS};
    parentingHandle = OS.PtCreateWidget(OS.PtContainer(), parentHandle, args.length / 3, args);
    if (parentingHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    args =
        new int[] {
          OS.Pt_ARG_FLAGS,
          (style & SWT.NO_FOCUS) != 0 ? 0 : OS.Pt_GETS_FOCUS,
          OS.Pt_GETS_FOCUS,
          OS.Pt_ARG_FLAGS,
          hasBorder() ? OS.Pt_HIGHLIGHTED : 0,
          OS.Pt_HIGHLIGHTED,
          OS.Pt_ARG_TOOLBAR_FLAGS,
          0,
          OS.Pt_TOOLBAR_DRAGGABLE | OS.Pt_TOOLBAR_END_SEPARATOR,
          OS.Pt_ARG_RESIZE_FLAGS,
          0,
          OS.Pt_RESIZE_XY_BITS
        };
    handle = OS.PtCreateWidget(display.PtToolbar, parentingHandle, args.length / 3, args);
    if ((style & SWT.FLAT) != 0) {
      OS.PtSetResource(handle, Pt_ARG_BASIC_FLAGS, Pt_FLAT_FILL, Pt_FLAT_FILL);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
