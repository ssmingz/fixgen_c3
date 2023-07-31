class PlaceHold {
  static int atkTable_get_row_at_index(int atkObject, int index) {
    if (DEBUG) {
      print("-->atkTable_get_row_at_index");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      AccessibleTableEvent event = new AccessibleTableEvent(accessible);
      for (int i = 0, length = listeners.size(); i < length; i++) {
        AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
        listener.getColumnCount(event);
      }
      return event.count == 0 ? -1 : index / event.count;
    }
    int parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_row_at_index != 0)) {
      parentResult = ATK.call(iface.get_row_at_index, atkObject, index);
    }
    return parentResult;
  }
}
