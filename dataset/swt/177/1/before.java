class PlaceHold {
  int get_selection(int selectionIndex, long pStartOffset, long pEndOffset) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.index = selectionIndex;
    event.start = -1;
    event.end = -1;
    for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getSelection(event);
    }
    if ((event.start == (-1)) && (selectionIndex == 0)) {
      event.childID = ACC.CHILDID_SELF;
      event.offset = -1;
      event.length = 0;
      for (int i = 0; i < accessibleTextListenersSize(); i++) {
        AccessibleTextListener listener =
            ((AccessibleTextListener) (accessibleTextListeners.elementAt(i)));
        listener.getSelectionRange(event);
      }
      event.start = event.offset;
      event.end = event.offset + event.length;
    }
    if (DEBUG) {
      print(
          (((((this + ".IAccessibleText::get_selection(") + selectionIndex) + ") returning ")
                      + event.start)
                  + ", ")
              + event.end);
    }
    COM.MoveMemory(pStartOffset, new int[] {event.start}, 4);
    COM.MoveMemory(pEndOffset, new int[] {event.end}, 4);
    if (event.start == (-1)) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
