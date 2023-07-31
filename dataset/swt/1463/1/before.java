class PlaceHold {
  int kEventMouseDragged(int nextHandler, int theEvent, int userData) {
    if (isEnabledModal()) {
      int result =
          (sendMouseEvent(MouseMove, ((short) (0)), 0, 0, false, theEvent))
              ? OS.eventNotHandledErr
              : OS.noErr;
      if (isDisposed()) {
        return OS.noErr;
      }
      return result;
    }
    return OS.eventNotHandledErr;
  }
}
