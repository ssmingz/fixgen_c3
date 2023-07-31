class PlaceHold {
  int get_cellAt(int row, int column, long ppCell) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    event.row = row;
    event.column = column;
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener =
          ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
      listener.getCell(event);
    }
    Accessible accessible = event.accessible;
    if (DEBUG) {
      print(
          (((((this + ".IAccessibleTable2::get_cellAt(row=") + row) + ", column=") + column)
                  + ") returning ")
              + accessible);
    }
    if (accessible == null) {
      return COM.E_INVALIDARG;
    }
    accessible.AddRef();
    COM.MoveMemory(ppCell, new long[] {accessible.getAddress()}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
