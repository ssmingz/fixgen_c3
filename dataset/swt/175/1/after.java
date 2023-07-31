class PlaceHold {
  int setCaretOffset(int offset) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.offset = offset;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.setCaretOffset(event);
    }
    if (event.result != ACC.OK) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
