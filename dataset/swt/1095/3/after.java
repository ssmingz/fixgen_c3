class PlaceHold {
  int get_nSelections(long pNSelections) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.count = -1;
    for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
      AccessibleTextExtendedListener listener = accessibleTextExtendedListeners.get(i);
      listener.getSelectionCount(event);
    }
    if (event.count == (-1)) {
      event.childID = ACC.CHILDID_SELF;
      event.offset = -1;
      event.length = 0;
      for (int i = 0; i < accessibleTextListenersSize(); i++) {
        AccessibleTextListener listener = accessibleTextListeners.get(i);
        listener.getSelectionRange(event);
      }
      event.count = ((event.offset != (-1)) && (event.length > 0)) ? 1 : 0;
    }
    if (DEBUG) {
      print((this + ".IAccessibleText::get_nSelections returning ") + event.count);
    }
    COM.MoveMemory(pNSelections, new int[] {event.count}, 4);
    return COM.S_OK;
  }
}
