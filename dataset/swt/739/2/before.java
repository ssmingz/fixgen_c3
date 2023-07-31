class PlaceHold {
  static int atkValue_get_maximum_value(int atkObject, int value) {
    if (DEBUG) {
      print("-->atkValue_get_maximum_value");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    AtkValueIface iface = getValueIface(atkObject);
    if ((iface != null) && (iface.get_maximum_value != 0)) {
      ATK.call(iface.get_maximum_value, atkObject, value);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleValueListeners;
      AccessibleValueEvent event = new AccessibleValueEvent(accessible);
      event.value = getGValue(value);
      for (int i = 0, length = listeners.size(); i < length; i++) {
        AccessibleValueListener listener = ((AccessibleValueListener) (listeners.elementAt(i)));
        listener.getMaximumValue(event);
      }
      setGValue(value, event.value);
    }
    return 0;
  }
}
