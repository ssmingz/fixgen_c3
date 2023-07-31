class PlaceHold {
  void createScrolledHandle(int topHandle) {
    scrolledHandle = createScrollView(topHandle, style);
    if (scrolledHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) != 0) {
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
    } else {
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
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.addControl(handle, scrolledHandle);
    OS.HIViewSetVisible(handle, true);
  }
}
