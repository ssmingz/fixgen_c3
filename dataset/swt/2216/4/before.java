class PlaceHold {
  int kEventSearchFieldSearchClicked(int nextHandler, int theEvent, int userData) {
    int result = super.kEventSearchFieldSearchClicked(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    Event event = new Event();
    event.detail = SWT.ICON_SEARCH;
    postEvent(DefaultSelection, event);
    return result;
  }
}
