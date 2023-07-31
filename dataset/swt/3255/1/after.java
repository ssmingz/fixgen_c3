class PlaceHold {
  int get_hyperlink(int index, int ppHyperlink) {
    if (DEBUG) {
      print(this + ".IAccessibleHypertext::get_hyperlink");
    }
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.index = index;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getHyperlink(event);
    }
    Accessible accessible = event.accessible;
    if (accessible == null) {
      setIntVARIANT(ppHyperlink, VT_EMPTY, 0);
      return COM.E_INVALIDARG;
    }
    accessible.AddRef();
    COM.MoveMemory(ppHyperlink, new int[] {accessible.getAddress()}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
