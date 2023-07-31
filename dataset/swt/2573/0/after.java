class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    runSkin();
    runDeferredLayouts();
    boolean events = false;
    events |= runSettings();
    events |= runPopups();
    try {
      OS.gdk_threads_leave();
      events |= OS.g_main_context_iteration(0, false);
    } finally {
      OS.gdk_threads_enter();
    }
    if (events) {
      runDeferredEvents();
      return true;
    }
    return isDisposed() || runAsyncMessages(false);
  }
}
