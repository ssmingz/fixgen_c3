class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) == 0) {
      int border = ((style & SWT.BORDER) != 0) ? 1 : 0;
      int features = OS.kControlSupportsEmbedding;
      if ((style & SWT.NO_FOCUS) == 0) {
        features |= OS.kControlSupportsFocus | OS.kControlGetsFocusOnClick;
      }
      handle =
          OS.NewControl(
              0,
              new Rect(),
              null,
              false,
              ((short) (features)),
              ((short) (0)),
              ((short) (0)),
              ((short) (kControlUserPaneProc)),
              0);
      MacUtil.addControl(handle, handle);
      OS.HIViewSetVisible(handle, true);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
    } else {
      createScrolledHandle(handle);
    }
  }
}
