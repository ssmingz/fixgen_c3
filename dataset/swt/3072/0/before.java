class PlaceHold {
  void sendSelection() {
    NSWindow window = view.window();
    boolean disableFlush = target == null;
    try {
      if (disableFlush) {
        window.retain();
        window.disableFlushWindow();
      }
      int value = 0;
      if (target != null) {
        view.sendAction(actionSelector, target);
      } else {
        value = getSelection();
      }
      NSPoint point;
      NSEvent nsEvent = NSApplication.sharedApplication().currentEvent();
      if (nsEvent != null) {
        point = nsEvent.locationInWindow();
        if (nsEvent.window() == null) {
          point = window.convertScreenToBase(point);
        }
      } else {
        point = window.mouseLocationOutsideOfEventStream();
      }
      int hitPart = ((int) (((NSScroller) (view)).testPart(point)));
      Event event = new Event();
      switch (hitPart) {
        case OS.NSScrollerDecrementLine:
          value -= increment;
          event.detail = SWT.ARROW_UP;
          break;
        case OS.NSScrollerDecrementPage:
          value -= pageIncrement;
          event.detail = SWT.PAGE_UP;
          break;
        case OS.NSScrollerIncrementLine:
          value += increment;
          event.detail = SWT.ARROW_DOWN;
          break;
        case OS.NSScrollerIncrementPage:
          value += pageIncrement;
          event.detail = SWT.PAGE_DOWN;
          break;
        case OS.NSScrollerKnob:
          event.detail = SWT.DRAG;
          break;
      }
      if (target == null) {
        if (event.detail != SWT.DRAG) {
          setSelection(value);
        }
      }
      sendEvent(Selection, event);
    } finally {
      if (disableFlush) {
        window.enableFlushWindow();
        window.release();
      }
    }
  }
}
