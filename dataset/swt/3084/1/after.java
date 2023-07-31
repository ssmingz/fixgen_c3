class PlaceHold {
  int get_anchorTarget(int index, long pAnchorTarget) {
    if (DEBUG) {
      print(this + ".IAccessibleHyperlink::get_anchorTarget");
    }
    AccessibleHyperlinkEvent event = new AccessibleHyperlinkEvent(this);
    event.index = index;
    for (int i = 0; i < accessibleHyperlinkListenersSize(); i++) {
      AccessibleHyperlinkListener listener = accessibleHyperlinkListeners.get(i);
      listener.getAnchorTarget(event);
    }
    Accessible accessible = event.accessible;
    if (accessible != null) {
      accessible.AddRef();
      setPtrVARIANT(pAnchorTarget, VT_DISPATCH, accessible.getAddress());
      return COM.S_OK;
    }
    setStringVARIANT(pAnchorTarget, event.result);
    if (event.result == null) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
