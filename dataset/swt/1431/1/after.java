class PlaceHold {
  int kEventMouseDragged(int nextHandler, int theEvent, int userData) {
    if ((state & CANVAS) == 0) {
      if (isEnabledModal()) {
        int result =
            (sendMouseEvent(MouseMove, ((short) (0)), 0, 0, false, theEvent))
                ? OS.eventNotHandledErr
                : OS.noErr;
        if (isDisposed()) {
          return OS.noErr;
        }
        display.dragDetect(this);
        if (isDisposed()) {
          return OS.noErr;
        }
        return result;
      }
    }
    return OS.eventNotHandledErr;
  }
}
