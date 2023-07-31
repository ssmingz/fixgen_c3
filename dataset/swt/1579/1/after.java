class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    int xtContext = OS.XtDisplayToApplicationContext(xDisplay);
    int status = OS.XtAppPending(xtContext);
    if (status != 0) {
      if ((status & OS.XtIMTimer) != 0) {
        OS.XtAppProcessEvent(xtContext, XtIMTimer);
        status = OS.XtAppPending(xtContext);
      }
      if ((status & OS.XtIMAlternateInput) != 0) {
        OS.XtAppProcessEvent(xtContext, XtIMAlternateInput);
        status = OS.XtAppPending(xtContext);
      }
      if ((status & OS.XtIMXEvent) != 0) {
        OS.XtAppNextEvent(xtContext, xEvent);
        if (!filterEvent(xEvent)) {
          OS.XtDispatchEvent(xEvent);
        }
      }
      runDeferredEvents();
      return true;
    }
    return runAsyncMessages(false);
  }
}
