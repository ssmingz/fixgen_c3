class PlaceHold {
  public boolean readAndDispatch() {
    checkDevice();
    if (((sendEventCount == 0) && (loopCount == (poolCount - 1)))
        && (Callback.getEntryCount() == 0)) {
      removePool();
    }
    addPool();
    runSkin();
    runDeferredLayouts();
    loopCount++;
    boolean events = false;
    try {
      events |= runSettings();
      events |= runTimers();
      events |= runContexts();
      events |= runPopups();
      NSEvent event =
          application.nextEventMatchingMask(NSAnyEventMask, null, NSDefaultRunLoopMode, true);
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
      if (((sendEventCount == 0) && (loopCount == poolCount)) && (Callback.getEntryCount() == 0)) {
        addPool();
      }
    }
    return events;
  }
}
