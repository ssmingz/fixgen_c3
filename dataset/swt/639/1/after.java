class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int status = super.kEventMouseDown(nextHandler, theEvent, userData);
    if (status == OS.noErr) {
      return status;
    }
    dragging = false;
    status = OS.CallNextEventHandler(nextHandler, theEvent);
    if (dragging) {
      Event event = new Event();
      sendSelectionEvent(Selection, event, true);
    }
    dragging = false;
    return status;
  }
}
