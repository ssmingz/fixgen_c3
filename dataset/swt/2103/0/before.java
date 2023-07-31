class PlaceHold {
  public boolean sleep() {
    checkDevice();
    OS.PtFlush();
    OS.PtHold();
    OS.PtAppProcessEvent(app_context);
    runDeferredEvents();
    OS.PtRelease();
    return true;
  }
}
