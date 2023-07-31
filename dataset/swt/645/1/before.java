class PlaceHold {
  int kEventMouseMoved(int nextHandler, int theEvent, int userData) {
    if (isEnabledModal()) {
      sendMouseEvent(MouseMove, ((short) (0)), theEvent);
    }
    return OS.eventNotHandledErr;
  }
}
