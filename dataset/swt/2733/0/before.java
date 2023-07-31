class PlaceHold {
  static int atkTable_get_n_rows(int atkObject) {
    if (DEBUG) {
      print("-->atkTable_get_n_rows");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_n_rows != 0)) {
      parentResult = ATK.call(iface.get_n_rows, atkObject);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      AccessibleTableEvent event = new AccessibleTableEvent(accessible);
      event.count = ((int) (parentResult));
      for (int i = 0, length = listeners.size(); i < length; i++) {
        AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
        listener.getRowCount(event);
        parentResult = event.count;
      }
    }
    return parentResult;
  }
}
