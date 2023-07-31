class PlaceHold {
  void initializeDisplay() {
    focusCallback = new Callback(this, "focusProc", 4);
    focusProc = focusCallback.getAddress();
    if (focusProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowCallback = new Callback(this, "windowProc", 4);
    windowProc = windowCallback.getAddress();
    if (windowProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowTimerCallback = new Callback(this, "windowTimerProc", 2);
    windowTimerProc = windowTimerCallback.getAddress();
    if (windowTimerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    timerCallback = new Callback(this, "timerProc", 2);
    timerProc = timerCallback.getAddress();
    if (timerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    caretCallback = new Callback(this, "caretProc", 2);
    caretProc = caretCallback.getAddress();
    if (caretProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    mouseHoverCallback = new Callback(this, "mouseHoverProc", 2);
    mouseHoverProc = mouseHoverCallback.getAddress();
    if (mouseHoverProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    checkExposeCallback = new Callback(this, "checkExposeProc", 3);
    checkExposeProc = checkExposeCallback.getAddress();
    if (checkExposeProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    checkResizeCallback = new Callback(this, "checkResizeProc", 3);
    checkResizeProc = checkResizeCallback.getAddress();
    if (checkResizeProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    wakeCallback = new Callback(this, "wakeProc", 3);
    wakeProc = wakeCallback.getAddress();
    if (wakeProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    int[] filedes = new int[2];
    if (OS.pipe(filedes) != 0) {
      error(ERROR_NO_HANDLES);
    }
    read_fd = filedes[0];
    write_fd = filedes[1];
    int xtContext = OS.XtDisplayToApplicationContext(xDisplay);
    inputID = OS.XtAppAddInput(xtContext, read_fd, XtInputReadMask, wakeProc, 0);
    fd_set = new byte[OS.fd_set_sizeof()];
    int xmDisplay = OS.XmGetXmDisplay(xDisplay);
    int[] args =
        new int[] {
          OS.XmNenableThinThickness,
          1,
          OS.XmNdragInitiatorProtocolStyle,
          OS.XmDRAG_DYNAMIC,
          OS.XmNdragReceiverProtocolStyle,
          OS.XmDRAG_DYNAMIC
        };
    OS.XtSetValues(xmDisplay, args, args.length / 2);
    int xScreen = OS.XDefaultScreen(xDisplay);
    int widgetClass = OS.topLevelShellWidgetClass();
    shellHandle = OS.XtAppCreateShell(appName, appClass, widgetClass, xDisplay, null, 0);
    OS.XtSetMappedWhenManaged(shellHandle, false);
    OS.XtResizeWidget(
        shellHandle, OS.XDisplayWidth(xDisplay, xScreen), OS.XDisplayHeight(xDisplay, xScreen), 0);
    OS.XtRealizeWidget(shellHandle);
  }
}
