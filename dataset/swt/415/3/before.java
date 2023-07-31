class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int border = ((style & SWT.BORDER) != 0) ? 1 : 0;
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
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.addControl(handle, handle);
    OS.HIViewSetVisible(handle, true);
  }
}
