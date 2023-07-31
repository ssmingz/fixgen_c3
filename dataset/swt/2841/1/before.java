class PlaceHold {
  static long atkTable_get_n_columns(long atkObject) {
    if (DEBUG) {
      print("-->atkTable_get_n_columns");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    long parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_n_columns != 0)) {
      parentResult = ATK.call(iface.get_n_columns, atkObject);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTableEvent event = new AccessibleTableEvent(accessible);
        event.count = ((int) (parentResult));
        for (int i = 0; i < length; i++) {
          AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
          listener.getColumnCount(event);
          parentResult = event.count;
        }
      }
    }
    return parentResult;
  }
}
