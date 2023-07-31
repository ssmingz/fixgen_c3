class PlaceHold {
  int get_startIndex(long pIndex) {
    if (DEBUG) {
      print(this + ".IAccessibleHyperlink::get_startIndex");
    }
    AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
    for (int i = 0; i < accessibleHyperlinkListenersSize(); i++) {
      AccessibleHyperlinkListener listener =
          ((AccessibleHyperlinkListener) (accessibleHyperlinkListeners.elementAt(i)));
      listener.getStartIndex(event);
    }
    COM.MoveMemory(pIndex, new int[] {event.index}, 4);
    return COM.S_OK;
  }
}
