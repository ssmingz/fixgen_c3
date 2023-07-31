class PlaceHold {
  int selectRow(int row) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    event.row = row;
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener =
          ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
      listener.setSelectedRow(event);
    }
    if (DEBUG) {
      print(
          (this + ".IAccessibleTable2::selectRow() returning ")
              + (event.result == null ? "E_INVALIDARG" : event.result));
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
