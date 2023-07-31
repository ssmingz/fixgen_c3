class PlaceHold {
  void sendSelection() {
    Event event = new Event();
    int hitPart = ((int) (((NSScroller) (view)).hitPart()));
    int value = getSelection();
    switch (hitPart) {
      case OS.NSScrollerDecrementLine:
        event.detail = SWT.ARROW_UP;
        value -= increment;
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
    if (event.detail != SWT.DRAG) {
      setSelection(value);
    }
    sendSelectionEvent(Selection, event, true);
  }
}
