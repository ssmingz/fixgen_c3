class PlaceHold {
  int get_endIndex(long pIndex) {
    if (DEBUG) {
      print(this + ".IAccessibleHyperlink::get_endIndex");
    }
    AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
    for (int i = 0; i < accessibleHyperlinkListenersSize(); i++) {
      AccessibleHyperlinkListener listener = accessibleHyperlinkListeners.get(i);
      listener.getEndIndex(event);
    }
    COM.MoveMemory(pIndex, new int[] {event.index}, 4);
    return COM.S_OK;
  }
}
