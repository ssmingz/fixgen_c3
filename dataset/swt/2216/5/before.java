class PlaceHold {
  int kEventSearchFieldCancelClicked(int nextHandler, int theEvent, int userData) {
    int result = super.kEventSearchFieldCancelClicked(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    setText("");
    Event event = new Event();
    event.detail = SWT.ICON_CANCEL;
    postEvent(DefaultSelection, event);
    return result;
  }
}
