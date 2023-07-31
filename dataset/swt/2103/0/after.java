class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (getMessageCount() != 0) {
      return true;
    }
    OS.PtFlush();
    OS.PtHold();
    OS.PtAppProcessEvent(app_context);
    runDeferredEvents();
    OS.PtRelease();
    return true;
  }
}
