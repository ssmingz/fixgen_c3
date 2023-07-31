class PlaceHold {
  int get_hyperlink(int index, int ppHyperlink) {
    AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(this);
    event.index = index;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getHyperlink(event);
    }
    Accessible accessible = event.accessible;
    if (accessible != null) {
      accessible.AddRef();
      setPtrVARIANT(ppHyperlink, VT_DISPATCH, accessible.getAddress());
    }
    return COM.S_OK;
  }
}
