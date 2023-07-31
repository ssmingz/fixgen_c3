class PlaceHold {
  int kEventMenuClosed(int nextHandler, int theEvent, int userData) {
    int result = super.kEventMenuClosed(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    closed = true;
    sendEvent(Hide);
    return OS.eventNotHandledErr;
  }
}
