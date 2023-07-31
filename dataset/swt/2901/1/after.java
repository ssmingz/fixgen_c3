class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    runPopups();
    int status = OS.gtk_events_pending();
    if (status != 0) {
      int event = removeGdkEvent();
      if (event != 0) {
        eventProc(event, 0);
        OS.gdk_event_free(event);
      } else {
        OS.gtk_main_iteration();
      }
      runDeferredEvents();
      return true;
    }
    return runAsyncMessages();
  }
}
