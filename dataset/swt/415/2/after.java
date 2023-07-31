class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int parentHandle = parent.handle;
    handle =
        OS.NewControl(
            0,
            new Rect(),
            null,
            false,
            ((short) (0)),
            ((short) (0)),
            ((short) (100)),
            ((short) (kControlProgressBarProc)),
            0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.insertControl(handle, parentHandle, -1);
    if ((style & SWT.INDETERMINATE) != 0) {
      OS.SetControlData(
          handle, ((short) (0)), kControlProgressBarIndeterminateTag, 4, new int[] {-1});
    }
    OS.HIViewSetVisible(handle, true);
  }
}
