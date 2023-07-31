class PlaceHold {
  static int atkTable_get_row_at_index(int atkObject, int index) {
    if (DEBUG) {
      print((("-->atkTable_get_row_at_index: " + atkObject) + " ") + index);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTableEvent event = new AccessibleTableEvent(accessible);
        for (int i = 0; i < length; i++) {
          AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
          listener.getColumnCount(event);
        }
        int result = (event.count == 0) ? -1 : index / event.count;
        if (DEBUG) {
          print("---> " + result);
        }
        return result;
      }
    }
    int parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_row_at_index != 0)) {
      parentResult = ATK.call(iface.get_row_at_index, atkObject, index);
    }
    return parentResult;
  }
}
