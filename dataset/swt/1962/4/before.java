class PlaceHold {
  int get_rowDescription(int row, long pbstrDescription) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    event.row = row;
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener =
          ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
      listener.getRowDescription(event);
    }
    if (DEBUG) {
      print(
          (((this + ".IAccessibleTable2::get_rowDescription(row=") + row) + ") returning ")
              + event.result);
    }
    setString(pbstrDescription, event.result);
    if (event.result == null) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
