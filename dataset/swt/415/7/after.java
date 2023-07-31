class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int borderWidth = ((style & SWT.BORDER) != 0) ? 1 : 0;
    if ((style & SWT.SEPARATOR) != 0) {
      handle =
          OS.NewControl(
              0,
              new Rect(),
              null,
              false,
              ((short) (0)),
              ((short) (0)),
              ((short) (100)),
              ((short) (kControlSeparatorLineProc)),
              0);
    } else {
      handle =
          OS.NewControl(
              0,
              new Rect(),
              null,
              false,
              ((short) (OS.kControlSupportsFocus | OS.kControlGetsFocusOnClick)),
              ((short) (0)),
              ((short) (0)),
              ((short) (kControlUserPaneProc)),
              0);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.insertControl(handle, handle, -1);
    if ((style & SWT.SEPARATOR) != 0) {
      if ((style & SWT.HORIZONTAL) != 0) {
        OS.SizeControl(handle, ((short) (20)), ((short) (1)));
      } else {
        OS.SizeControl(handle, ((short) (1)), ((short) (20)));
      }
    }
    OS.HIViewSetVisible(handle, true);
  }
}
