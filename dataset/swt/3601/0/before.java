class PlaceHold {
  int kEventMenuOpening(int nextHandler, int theEvent, int userData) {
    int result = super.kEventMenuOpening(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    closed = false;
    sendEvent(Show);
    modified = false;
    return OS.eventNotHandledErr;
  }
}
