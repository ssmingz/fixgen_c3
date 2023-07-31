class PlaceHold {
  static int atkValue_get_minimum_value(int atkObject, int value) {
    if (DEBUG) {
      print("-->atkValue_get_minimum_value");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    AtkValueIface iface = getValueIface(atkObject);
    if ((iface != null) && (iface.get_minimum_value != 0)) {
      ATK.call(iface.get_minimum_value, atkObject, value);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleValueListeners;
      AccessibleValueEvent event = new AccessibleValueEvent(accessible);
      event.value = getGValue(value);
      for (int i = 0, length = listeners.size(); i < length; i++) {
        AccessibleValueListener listener = ((AccessibleValueListener) (listeners.elementAt(i)));
        listener.getMinimumValue(event);
      }
      setGValue(value, event.value);
    }
    return 0;
  }
}
