class PlaceHold {
  int setCaretOffset(int offset) {
    AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(this);
    event.index = offset;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.setCaretOffset(event);
    }
    return COM.S_OK;
  }
}
