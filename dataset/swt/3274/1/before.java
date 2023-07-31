class PlaceHold {
  static long atkTable_get_caption(long atkObject) {
    if (DEBUG) {
      print("-->atkTable_get_caption");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTableListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTableEvent event = new AccessibleTableEvent(accessible);
        for (int i = 0; i < length; i++) {
          AccessibleTableListener listener = ((AccessibleTableListener) (listeners.elementAt(i)));
          listener.getCaption(event);
        }
        Accessible result = event.accessible;
        if (result != null) {
          return result.getAccessibleObject().handle;
        }
      }
    }
    long parentResult = 0;
    AtkTableIface iface = getTableIface(atkObject);
    if ((iface != null) && (iface.get_caption != 0)) {
      parentResult = ATK.call(iface.get_caption, atkObject);
    }
    return parentResult;
  }
}
