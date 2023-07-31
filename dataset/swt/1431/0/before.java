class PlaceHold {
  int kEventMouseMoved(int nextHandler, int theEvent, int userData) {
    if (isEnabledModal()) {
      return sendMouseEvent(MouseMove, ((short) (0)), 0, 0, true, theEvent)
          ? OS.eventNotHandledErr
          : OS.noErr;
    }
    return OS.eventNotHandledErr;
  }
}
