class PlaceHold {
  id getMinValueAttribute(int childID) {
    id returnValue = null;
    if (accessibleValueListenersSize() > 0) {
      AccessibleValueEvent event = new AccessibleValueEvent(this);
      for (int i = 0; i < accessibleValueListenersSize(); i++) {
        AccessibleValueListener listener =
            ((AccessibleValueListener) (accessibleValueListeners.elementAt(i)));
        listener.getMinimumValue(event);
      }
      returnValue = NSNumber.numberWithDouble(event.value.doubleValue());
    }
    return returnValue;
  }
}
