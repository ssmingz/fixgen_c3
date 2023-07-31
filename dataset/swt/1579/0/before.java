class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    boolean events = runPopups();
    events |= OS.g_main_context_iteration(0, false);
    if (events) {
      runDeferredEvents();
      return true;
    }
    return runAsyncMessages();
  }
}
