class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    short procID =
        ((short)
            ((OS.kControlSliderProc + OS.kControlSliderLiveFeedback)
                + OS.kControlSliderNonDirectional));
    handle =
        OS.NewControl(
            0, new Rect(), null, false, ((short) (0)), ((short) (0)), ((short) (100)), procID, 0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    MacUtil.insertControl(handle, handle, -1);
    OS.HIViewSetVisible(handle, true);
  }
}
