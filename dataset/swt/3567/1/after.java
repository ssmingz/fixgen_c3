class PlaceHold {
  static int atkTable_get_row_header(int atkObject, int row) {
    if (DEBUG) {
      System.out.println("-->atkTable_get_row_header");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_row_header != 0)) {
      parentResult = ATK.call(iface.get_row_header, atkObject, row);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTableEvent event = new AccessibleTableEvent(accessible);
        for (int i = 0; i < length; i++) {
          AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
          listener.getRowHeaders(event);
        }
        Accessible[] accessibles = event.accessibles;
        if (accessibles != null) {
          if ((0 <= row) && (row < accessibles.length)) {
            return accessibles[((int) (row))].getAccessibleObject().handle;
          }
        }
      }
    }
    return parentResult;
  }
}
