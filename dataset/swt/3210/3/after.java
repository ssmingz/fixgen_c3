class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    runSkin();
    runDeferredLayouts();
    boolean events = false;
    events |= runSettings();
    events |= runPopups();
    OS.gdk_threads_leave();
    events |= OS.g_main_context_iteration(0, false);
    if (events) {
      runDeferredEvents();
      return true;
    }
    return isDisposed() || runAsyncMessages(false);
  }
}
