class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    if ((sendEventCount == 0) && (loopCount == (poolCount - 1))) {
      removePool();
    }
    addPool();
    loopCount++;
    boolean events = false;
    try {
      events |= runSettings();
      events |= runTimers();
      events |= runContexts();
      events |= runPopups();
      NSEvent event = application.nextEventMatchingMask(0, null, NSDefaultRunLoopMode, true);
      if (event != null) {
        events = true;
        application.sendEvent(event);
      }
      events |= runPaint();
      events |= runDeferredEvents();
      if (!events) {
        events = isDisposed() || runAsyncMessages(false);
      }
    } finally {
      removePool();
      loopCount--;
      if ((sendEventCount == 0) && (loopCount == poolCount)) {
        addPool();
      }
    }
    return events;
  }
}
