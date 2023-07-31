class PlaceHold {
  int kEventMouseDragged(int nextHandler, int theEvent, int userData) {
    if ((state & CANVAS) == 0) {
      if (isEnabledModal()) {
        sendMouseEvent(MouseMove, ((short) (0)), 0, 0, false, theEvent);
      }
      display.dragDetect(this);
    }
    return OS.eventNotHandledErr;
  }
}
