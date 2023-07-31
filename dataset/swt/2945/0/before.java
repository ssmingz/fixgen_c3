class PlaceHold {
  static long atkTable_get_row_description(long atkObject, long row) {
    if (DEBUG) {
      print("-->atkTable_get_row_description");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    long parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_row_description != 0)) {
      parentResult = ATK.call(iface.get_row_description, atkObject, row);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTableEvent event = new AccessibleTableEvent(accessible);
        event.row = ((int) (row));
        if (parentResult != 0) {
          event.result = getString(parentResult);
        }
        for (int i = 0; i < length; i++) {
          AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
          listener.getRowDescription(event);
        }
        if (event.result == null) {
          return parentResult;
        }
        if (descriptionPtr != (-1)) {
          OS.g_free(descriptionPtr);
        }
        return descriptionPtr = getStringPtr(event.result);
      }
    }
    return parentResult;
  }
}
