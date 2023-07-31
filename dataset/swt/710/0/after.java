class PlaceHold {
  void initializeDisplay() {
    windowCallback = new Callback(this, "windowProc", 3);
    windowProc = windowCallback.getAddress();
    if (windowProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    drawCallback = new Callback(this, "drawProc", 2);
    drawProc = drawCallback.getAddress();
    if (drawProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    workCallback = new Callback(this, "workProc", 1);
    workProc = workCallback.getAddress();
    if (workProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    inputCallback = new Callback(this, "inputProc", 4);
    inputProc = inputCallback.getAddress();
    if (inputProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    timerCallback = new Callback(this, "timerProc", 3);
    timerProc = timerCallback.getAddress();
    if (timerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    hotkeyCallback = new Callback(this, "hotkeyProc", 3);
    hotkeyProc = hotkeyCallback.getAddress();
    if (hotkeyProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    pulse = OS.PtAppCreatePulse(app_context, -1);
    input = OS.PtAppAddInput(app_context, pulse, inputProc, 0);
    int[] args =
        new int[] {
          OS.Pt_ARG_REGION_OPAQUE,
          0,
          ~0,
          OS.Pt_ARG_FILL_COLOR,
          OS.Pg_TRANSPARENT,
          0,
          OS.Pt_ARG_REGION_SENSE,
          OS.Ph_EV_TIMER,
          ~0
        };
    OS.PtSetParentWidget(0);
    timerHandle = OS.PtCreateWidget(OS.PtRegion(), 0, args.length / 3, args);
    if (timerHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.PtRealizeWidget(timerHandle);
  }
}
