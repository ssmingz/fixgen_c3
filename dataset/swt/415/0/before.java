class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    state &= ~CANVAS;
    handle =
        OS.NewControl(
            0,
            new Rect(),
            null,
            false,
            ((short) (0)),
            ((short) (0)),
            ((short) (0)),
            ((short) (kControlTabSmallProc)),
            0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.addControl(handle, handle);
    OS.HIViewSetVisible(handle, true);
  }
}
