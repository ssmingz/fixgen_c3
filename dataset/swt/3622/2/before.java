class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    Display display = getDisplay();
    int clazz = display.PtList;
    int parentHandle = parent.parentingHandle();
    int mode = OS.Pt_SELECTION_MODE_SINGLE | OS.Pt_SELECTION_MODE_AUTO;
    if ((style & SWT.MULTI) != 0) {
      if ((style & SWT.SIMPLE) != 0) {
        mode =
            ((OS.Pt_SELECTION_MODE_MULTIPLE | OS.Pt_SELECTION_MODE_NOCLEAR)
                    | OS.Pt_SELECTION_MODE_TOGGLE)
                | OS.Pt_SELECTION_MODE_NOMOVE;
      } else {
        mode = OS.Pt_SELECTION_MODE_MULTIPLE | OS.Pt_SELECTION_MODE_AUTO;
      }
    }
    mode |= OS.Pt_SELECTION_MODE_NOFOCUS;
    boolean hasBorder = (style & SWT.BORDER) != 0;
    int listFlags = OS.Pt_LIST_SCROLLBAR_ALWAYS | OS.Pt_LIST_SCROLLBAR_AS_REQUIRED;
    int[] args =
        new int[] {
          OS.Pt_ARG_FLAGS,
          hasBorder ? OS.Pt_HIGHLIGHTED : 0,
          OS.Pt_HIGHLIGHTED,
          OS.Pt_ARG_SELECTION_MODE,
          mode,
          0,
          OS.Pt_ARG_FLAGS,
          OS.Pt_SELECTABLE | OS.Pt_SELECT_NOREDRAW,
          OS.Pt_SELECTABLE | OS.Pt_SELECT_NOREDRAW,
          OS.Pt_ARG_LIST_FLAGS,
          (style & SWT.V_SCROLL) != 0 ? OS.Pt_LIST_SCROLLBAR_AS_REQUIRED : 0,
          listFlags,
          OS.Pt_ARG_RESIZE_FLAGS,
          0,
          OS.Pt_RESIZE_XY_BITS
        };
    handle = OS.PtCreateWidget(clazz, parentHandle, args.length / 3, args);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    createStandardScrollBars();
  }
}
