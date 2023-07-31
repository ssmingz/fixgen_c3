class PlaceHold {
  public void internal_accessibilitySetValue_forAttribute(
      id value, NSString attribute, int childId) {
    if (attribute.isEqualToString(NSAccessibilitySelectedTextRangeAttribute)) {
      setSelectedTextRangeAttribute(value, childId);
    }
    if (attribute.isEqualToString(NSAccessibilitySelectedTextAttribute)) {
      setSelectedTextAttribute(value, childId);
    }
    if (attribute.isEqualToString(NSAccessibilityVisibleCharacterRangeAttribute)) {
      setVisibleCharacterRangeAttribute(value, childId);
    }
    if (accessibleValueListenersSize() > 0) {
      AccessibleValueEvent event = new AccessibleValueEvent(this);
      NSNumber number = new NSNumber(value);
      event.value = new Double(number.doubleValue());
      for (int i = 0; i < accessibleValueListenersSize(); i++) {
        AccessibleValueListener listener =
            ((AccessibleValueListener) (accessibleValueListeners.elementAt(i)));
        listener.setCurrentValue(event);
      }
    }
  }
}
