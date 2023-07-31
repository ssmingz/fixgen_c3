class PlaceHold {
  int get_currentValue(long pCurrentValue) {
    AccessibleValueEvent event = new AccessibleValueEvent(this);
    for (int i = 0; i < accessibleValueListenersSize(); i++) {
      AccessibleValueListener listener = accessibleValueListeners.get(i);
      listener.getCurrentValue(event);
    }
    if (DEBUG) {
      print(
          ((this + ".IAccessibleValue::get_currentValue returning ") + event.value)
              + hresult(event.value == null ? COM.S_FALSE : COM.S_OK));
    }
    setNumberVARIANT(pCurrentValue, event.value);
    return COM.S_OK;
  }
}
