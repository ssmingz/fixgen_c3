class PlaceHold {
  static long atkHypertext_get_link(long atkObject, long link_index) {
    if (DEBUG) {
      print("-->atkHypertext_get_link");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      List<AccessibleTextExtendedListener> listeners = accessible.accessibleTextExtendedListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        event.index = ((int) (link_index));
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener = listeners.get(i);
          listener.getHyperlink(event);
        }
        Accessible result = event.accessible;
        return result != null ? result.getAccessibleObject().handle : 0;
      }
    }
    long parentResult = 0;
    AtkHypertextIface iface = getHypertextIface(atkObject);
    if ((iface != null) && (iface.get_link != 0)) {
      parentResult = ATK.call(iface.get_link, atkObject, link_index);
    }
    return parentResult;
  }
}
