class PlaceHold {
  void createHandle() {
    int actionProc = display.actionProc;
    int[] outControl = new int[1];
    int window = OS.GetControlOwner(handle);
    OS.CreateSliderControl(
        window,
        null,
        0,
        0,
        100,
        kControlSliderDoesNotPoint,
        ((short) (0)),
        true,
        actionProc,
        outControl);
    if (outControl[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = outControl[0];
    if ((style & SWT.VERTICAL) != 0) {
      OS.SetControl32BitValue(handle, 100);
    }
  }
}
