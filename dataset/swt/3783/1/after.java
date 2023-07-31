class PlaceHold {
  id getRangeForIndexParameterizedAttribute(id parameter, int childID) {
    id returnValue = null;
    NSNumber charNumberObj = new NSNumber(parameter.id);
    int charNumber = charNumberObj.intValue();
    if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      event.start = event.end = 0;
      event.count = charNumber;
      event.type = ACC.TEXT_BOUNDARY_CHAR;
      for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
        AccessibleTextExtendedListener listener =
            ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
        listener.getText(event);
      }
      NSRange range = new NSRange();
      range.location = event.start;
      range.length = event.end - event.start;
      returnValue = NSValue.valueWithRange(range);
    } else if (accessibleControlListenersSize() > 0) {
    }
    return returnValue;
  }
}
