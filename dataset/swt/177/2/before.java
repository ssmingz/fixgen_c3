class PlaceHold {
  int getCaretOffset() {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.offset = -1;
    for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
      AccessibleTextListener listener =
          ((AccessibleTextListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getCaretOffset(event);
    }
    if (event.offset == (-1)) {
      for (int i = 0; i < accessibleTextListenersSize(); i++) {
        event.childID = ACC.CHILDID_SELF;
        AccessibleTextListener listener =
            ((AccessibleTextListener) (accessibleTextListeners.elementAt(i)));
        listener.getCaretOffset(event);
      }
    }
    return event.offset;
  }
}
