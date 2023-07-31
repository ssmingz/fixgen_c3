class PlaceHold {
  int get_maximumValue(long pMaximumValue) {
    AccessibleValueEvent event = new AccessibleValueEvent(this);
    for (int i = 0; i < accessibleValueListenersSize(); i++) {
      AccessibleValueListener listener =
          ((AccessibleValueListener) (accessibleValueListeners.elementAt(i)));
      listener.getMaximumValue(event);
    }
    if (DEBUG) {
      print(
          ((this + ".IAccessibleValue::get_maximumValue returning ") + event.value)
              + hresult(event.value == null ? COM.S_FALSE : COM.S_OK));
    }
    setNumberVARIANT(pMaximumValue, event.value);
    return COM.S_OK;
  }
}
