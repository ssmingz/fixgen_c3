class PlaceHold {
  id getStringForRangeParameterizedAttribute(id parameter, int childID) {
    id returnValue = null;
    NSValue parameterObject = new NSValue(parameter.id);
    NSRange range = parameterObject.rangeValue();
    if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      event.start = ((int) (range.location));
      event.end = ((int) (range.location + range.length));
      event.type = ACC.TEXT_BOUNDARY_ALL;
      for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
        AccessibleTextExtendedListener listener =
            ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
        listener.getText(event);
      }
      if (event.result != null) {
        returnValue = NSString.stringWith(event.result);
      }
    } else if (accessibleControlListenersSize() > 0) {
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = childID;
      event.result = null;
      for (int i = 0; i < accessibleControlListenersSize(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getValue(event);
      }
      String appValue = event.result;
      if (appValue != null) {
        returnValue =
            NSString.stringWith(
                appValue.substring(
                    ((int) (range.location)), ((int) (range.location + range.length))));
      }
    }
    return returnValue;
  }
}
