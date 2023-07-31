class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    drawMenuBars();
    runPopups();
    if (OS.PeekMessage(msg, 0, 0, 0, PM_REMOVE)) {
      if (!filterMessage(msg)) {
        OS.TranslateMessage(msg);
        OS.DispatchMessage(msg);
      }
      runDeferredEvents();
      return true;
    }
    return runAsyncMessages(false);
  }
}
