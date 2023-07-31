class PlaceHold {
  int get_selection(int selectionIndex, int pStartOffset, int pEndOffset) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.index = selectionIndex;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getSelection(event);
    }
    COM.MoveMemory(pStartOffset, new int[] {event.start}, 4);
    COM.MoveMemory(pEndOffset, new int[] {event.end}, 4);
    return COM.S_OK;
  }
}
