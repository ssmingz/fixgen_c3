class PlaceHold {
  id getMaxValueAttribute(int childID) {
    id returnValue = null;
    if (accessibleValueListenersSize() > 0) {
      AccessibleValueEvent event = new AccessibleValueEvent(this);
      for (int i = 0; i < accessibleValueListenersSize(); i++) {
        AccessibleValueListener listener = accessibleValueListeners.get(i);
        listener.getMaximumValue(event);
      }
      returnValue = NSNumber.numberWithDouble(event.value.doubleValue());
    }
    return returnValue;
  }
}
