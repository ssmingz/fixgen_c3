class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    idle = false;
    OS.PtRelease();
    OS.PtHold();
    int id = OS.PtAppAddWorkProc(app_context, workProc, 0);
    OS.PtAppProcessEvent(app_context);
    OS.PtAppRemoveWorkProc(app_context, id);
    boolean result = true;
    if (idle) {
      result = runAsyncMessages();
    } else {
      runDeferredEvents();
    }
    OS.PtRelease();
    OS.PtHold();
    return result;
  }
}
