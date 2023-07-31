class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    Display display = getDisplay();
    int parentHandle = parent.handle;
    if ((style & SWT.ARROW) != 0) {
      int[] args =
          new int[] {
            OS.Pt_ARG_BASIC_FLAGS,
            OS.Pt_HORIZONTAL_GRADIENT,
            OS.Pt_STATIC_GRADIENT | OS.Pt_HORIZONTAL_GRADIENT,
            OS.Pt_ARG_RESIZE_FLAGS,
            0,
            OS.Pt_RESIZE_XY_BITS
          };
      handle = OS.PtCreateWidget(display.PtButton, parentHandle, args.length / 3, args);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      return;
    }
    int alignment = OS.Pt_LEFT;
    if ((style & SWT.CENTER) != 0) {
      alignment = OS.Pt_CENTER;
    }
    if ((style & SWT.RIGHT) != 0) {
      alignment = OS.Pt_RIGHT;
    }
    if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
      int[] args =
          new int[] {
            OS.Pt_ARG_HORIZONTAL_ALIGNMENT,
            alignment,
            0,
            OS.Pt_ARG_INDICATOR_TYPE,
            (style & SWT.CHECK) != 0 ? OS.Pt_N_OF_MANY : OS.Pt_ONE_OF_MANY,
            0,
            OS.Pt_ARG_FILL_COLOR,
            display.WIDGET_BACKGROUND,
            0,
            OS.Pt_ARG_RESIZE_FLAGS,
            0,
            OS.Pt_RESIZE_XY_BITS
          };
      handle = OS.PtCreateWidget(display.PtToggleButton, parentHandle, args.length / 3, args);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      return;
    }
    int[] args =
        new int[] {
          OS.Pt_ARG_FLAGS,
          (style & SWT.TOGGLE) != 0 ? OS.Pt_TOGGLE : 0,
          OS.Pt_TOGGLE,
          OS.Pt_ARG_HORIZONTAL_ALIGNMENT,
          alignment,
          0,
          OS.Pt_ARG_RESIZE_FLAGS,
          0,
          OS.Pt_RESIZE_XY_BITS
        };
    handle = OS.PtCreateWidget(display.PtButton, parentHandle, args.length / 3, args);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
