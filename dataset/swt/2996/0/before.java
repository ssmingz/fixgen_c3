class PlaceHold {
  int scrollSubstringTo(int startIndex, int endIndex, int scrollType) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.start = startIndex;
    event.end = endIndex;
    switch (scrollType) {
      case COM.IA2_SCROLL_TYPE_TOP_LEFT:
        event.type = ACC.SCROLL_TYPE_TOP_LEFT;
        break;
      case COM.IA2_SCROLL_TYPE_BOTTOM_RIGHT:
        event.type = ACC.SCROLL_TYPE_BOTTOM_RIGHT;
        break;
      case COM.IA2_SCROLL_TYPE_TOP_EDGE:
        event.type = ACC.SCROLL_TYPE_TOP_EDGE;
        break;
      case COM.IA2_SCROLL_TYPE_BOTTOM_EDGE:
        event.type = ACC.SCROLL_TYPE_BOTTOM_EDGE;
        break;
      case COM.IA2_SCROLL_TYPE_LEFT_EDGE:
        event.type = ACC.SCROLL_TYPE_LEFT_EDGE;
        break;
      case COM.IA2_SCROLL_TYPE_RIGHT_EDGE:
        event.type = ACC.SCROLL_TYPE_RIGHT_EDGE;
        break;
      case COM.IA2_SCROLL_TYPE_ANYWHERE:
        event.type = ACC.SCROLL_TYPE_ANYWHERE;
        break;
    }
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.scrollText(event);
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
