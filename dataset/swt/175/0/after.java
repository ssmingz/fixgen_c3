class PlaceHold {
  int scrollSubstringToPoint(int startIndex, int endIndex, int coordinateType, int x, int y) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.start = startIndex;
    event.end = endIndex;
    event.type = ACC.SCROLL_TYPE_POINT;
    event.x = x;
    event.y = y;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.scrollText(event);
    }
    if (event.result != ACC.OK) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
