class PlaceHold {
  int kEventMouseDragged(int nextHandler, int theEvent, int userData) {
    if (isEnabledModal()) {
      if (display.dragging) {
        display.dragging = false;
        sendDragEvent(dragX, dragY);
        if (isDisposed()) {
          return OS.noErr;
        }
      }
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
