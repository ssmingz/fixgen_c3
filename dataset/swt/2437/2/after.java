class PlaceHold {
  static long atkValue_get_current_value(long atkObject, long value) {
    if (DEBUG) {
      print("-->atkValue_get_current_value");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    AtkValueIface iface = getValueIface(atkObject);
    if ((iface != null) && (iface.get_current_value != 0)) {
      ATK.call(iface.get_current_value, atkObject, value);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      List<AccessibleValueListener> listeners = accessible.accessibleValueListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleValueEvent event = new AccessibleValueEvent(accessible);
        event.value = getGValue(value);
        for (int i = 0; i < length; i++) {
          AccessibleValueListener listener = listeners.get(i);
          listener.getCurrentValue(event);
        }
        setGValue(value, event.value);
      }
    }
    return 0;
  }
}
