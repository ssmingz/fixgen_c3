class PlaceHold {
  int get_minimumValue(long pMinimumValue) {
    AccessibleValueEvent event = new AccessibleValueEvent(this);
    for (int i = 0; i < accessibleValueListenersSize(); i++) {
      AccessibleValueListener listener = accessibleValueListeners.get(i);
      listener.getMinimumValue(event);
    }
    if (DEBUG) {
      print(
          ((this + ".IAccessibleValue::get_minimumValue returning ") + event.value)
              + hresult(event.value == null ? COM.S_FALSE : COM.S_OK));
    }
    setNumberVARIANT(pMinimumValue, event.value);
    return COM.S_OK;
  }
}
