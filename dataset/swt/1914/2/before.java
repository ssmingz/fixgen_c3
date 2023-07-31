class PlaceHold {
  int kEventTextInputPosToOffset(int nextHandler, int theEvent, int userData) {
    if (!isInlineIMEEnabled()) {
      return OS.eventNotHandledErr;
    }
    if (startOffset == (-1)) {
      return OS.eventNotHandledErr;
    }
    Point pt = new Point();
    int sizeof = Point.sizeof;
    OS.GetEventParameter(
        theEvent, kEventParamTextInputSendCurrentPoint, typeQDPoint, null, sizeof, null, pt);
    Point point = parent.toControl(pt.h, pt.v);
    Event event = new Event();
    event.detail = SWT.COMPOSITION_OFFSET;
    event.x = point.x;
    event.y = point.y;
    sendEvent(ImeComposition, event);
    int hitTest;
    int offset = event.index + event.count;
    if (offset == (-1)) {
      hitTest = OS.kTSMOutsideOfBody;
    } else if ((startOffset <= offset) && (offset < (startOffset + text.length()))) {
      hitTest = OS.kTSMInsideOfActiveInputArea;
      offset -= startOffset;
    } else {
      hitTest = OS.kTSMInsideOfBody;
    }
    OS.SetEventParameter(
        theEvent, kEventParamTextInputReplyTextOffset, typeLongInteger, 4, new int[] {offset * 2});
    OS.SetEventParameter(
        theEvent, kEventParamTextInputReplyRegionClass, typeLongInteger, 4, new int[] {hitTest});
    OS.SetEventParameter(
        theEvent,
        kEventParamTextInputReplyLeadingEdge,
        typeBoolean,
        4,
        new boolean[] {event.count == 0});
    return OS.noErr;
  }
}
