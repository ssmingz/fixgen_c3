class PlaceHold {
  int selectColumn(int column) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    event.column = column;
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener = accessibleTableListeners.get(i);
      listener.setSelectedColumn(event);
    }
    if (DEBUG) {
      print(
          (this + ".IAccessibleTable2::selectColumn() returning ")
              + (event.result == null ? "E_INVALIDARG" : event.result));
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
