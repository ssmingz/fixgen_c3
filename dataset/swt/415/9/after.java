class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    handle =
        OS.NewControl(
            0,
            new Rect(),
            null,
            false,
            ((short) (0)),
            ((short) (0)),
            ((short) (0)),
            ((short) (kControlGroupBoxTextTitleProc)),
            0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.insertControl(handle, handle, -1);
    OS.HIViewSetVisible(handle, true);
    setFont(defaultFont());
  }
}
