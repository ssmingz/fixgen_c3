class PlaceHold {
  int get_offsetAtPoint(int x, int y, int coordType, int pOffset) {
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.x = x;
    event.y = y;
    for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getOffsetAtPoint(event);
    }
    COM.MoveMemory(pOffset, new int[] {event.index}, 4);
    return COM.S_OK;
  }
}
