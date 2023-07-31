class PlaceHold {
  int kEventMouseMoved(int nextHandler, int theEvent, int userData) {
    if (isEnabledModal()) {
      sendMouseEvent(MouseMove, ((short) (0)), 0, 0, false, theEvent);
    }
    return OS.eventNotHandledErr;
  }
}
