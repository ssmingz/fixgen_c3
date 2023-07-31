class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    boolean events = false;
    events |= runSettings();
    events |= runTimers();
    events |= runPopups();
    int[] outEvent = new int[1];
    int status = OS.ReceiveNextEvent(0, null, kEventDurationNoWait, true, outEvent);
    if (status == OS.noErr) {
      events = true;
      OS.SendEventToEventTarget(outEvent[0], OS.GetEventDispatcherTarget());
      OS.ReleaseEvent(outEvent[0]);
      if ((focusCombo != null) && (!focusCombo.isDisposed())) {
        focusCombo.checkSelection();
      }
    }
    events |= runPaint();
    if (events) {
      runEnterExit();
      runDeferredEvents();
      return true;
    }
    return isDisposed() || runAsyncMessages(false);
  }
}
