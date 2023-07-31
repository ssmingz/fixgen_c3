class PlaceHold {
  static long atkTable_add_row_selection(long atkObject, long row) {
    if (DEBUG) {
      print("-->atkTable_add_row_selection");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTableEvent event = new AccessibleTableEvent(accessible);
        event.row = ((int) (row));
        for (int i = 0; i < length; i++) {
          AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
          listener.selectRow(event);
        }
        return OK.equals(event.result) ? 1 : 0;
      }
    }
    long parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.add_row_selection != 0)) {
      parentResult = ATK.call(iface.add_row_selection, atkObject, row);
    }
    return parentResult;
  }
}
