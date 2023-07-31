class PlaceHold {
  int kEventTextInputOffsetToPos(int nextHandler, int theEvent, int userData) {
    if (!isInlineEnabled()) {
      return OS.eventNotHandledErr;
    }
    Caret caret = parent.caret;
    if (caret == null) {
      return OS.eventNotHandledErr;
    }
    Point pt = new Point();
    int sizeof = Point;
    Point point = parent.toDisplay(caret.x, caret.y + caret.height);
    pt.h = ((short) (point.x));
    pt.v = ((short) (point.y));
    OS.SetEventParameter(theEvent, kEventParamTextInputReplyPoint, typeQDPoint, sizeof, pt);
    return OS.noErr;
  }
}
