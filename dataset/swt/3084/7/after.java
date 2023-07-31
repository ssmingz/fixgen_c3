class PlaceHold {
  int get_anchor(int index, long pAnchor) {
    if (DEBUG) {
      print(this + ".IAccessibleHyperlink::get_anchor");
    }
    AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
    event.index = index;
    for (int i = 0; i < accessibleHyperlinkListenersSize(); i++) {
      AccessibleHyperlinkListener listener = accessibleHyperlinkListeners.get(i);
      listener.getAnchor(event);
    }
    Accessible accessible = event.accessible;
    if (accessible != null) {
      accessible.AddRef();
      setPtrVARIANT(pAnchor, VT_DISPATCH, accessible.getAddress());
      return COM.S_OK;
    }
    setStringVARIANT(pAnchor, event.result);
    if (event.result == null) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
