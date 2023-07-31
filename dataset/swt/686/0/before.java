class PlaceHold {
  int removeSelection(int selectionIndex) {
    AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(this);
    event.index = selectionIndex;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.removeSelection(event);
    }
    return COM.S_OK;
  }
}
