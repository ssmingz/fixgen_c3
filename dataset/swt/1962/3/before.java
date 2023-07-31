class PlaceHold {
  int get_nSelectedRows(long pRowCount) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener =
          ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
      listener.getSelectedRowCount(event);
    }
    if (DEBUG) {
      print((this + ".IAccessibleTable2::get_nSelectedRows() returning ") + event.count);
    }
    COM.MoveMemory(pRowCount, new int[] {event.count}, 4);
    return COM.S_OK;
  }
}
