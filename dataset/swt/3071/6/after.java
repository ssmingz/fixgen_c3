class PlaceHold {
  int setCurrentValue(long value) {
    if (DEBUG) {
      print(this + ".IAccessibleValue::setCurrentValue");
    }
    AccessibleValueEvent event = new AccessibleValueEvent(this);
    event.value = getNumberVARIANT(value);
    for (int i = 0; i < accessibleValueListenersSize(); i++) {
      AccessibleValueListener listener = accessibleValueListeners.get(i);
      listener.setCurrentValue(event);
    }
    return COM.S_OK;
  }
}
