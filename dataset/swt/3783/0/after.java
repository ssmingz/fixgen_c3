class PlaceHold {
  id getLineForIndexParameterizedAttribute(id parameter, int childID) {
    id returnValue = null;
    NSNumber charNumberObj = new NSNumber(parameter.id);
    int charNumber = charNumberObj.intValue();
    if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      event.start = charNumber;
      event.end = charNumber;
      event.count = Integer.MIN_VALUE;
      event.type = ACC.TEXT_BOUNDARY_LINE;
      for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
        AccessibleTextExtendedListener listener =
            ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
        listener.getText(event);
      }
      returnValue = NSNumber.numberWithInt(Math.max(0, -event.count));
    } else {
      AccessibleControlEvent controlEvent = new AccessibleControlEvent(this);
      controlEvent.childID = childID;
      controlEvent.result = null;
      for (int i = 0; i < accessibleControlListenersSize(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getValue(controlEvent);
      }
      String text = controlEvent.result;
      if (text != null) {
        returnValue = NSNumber.numberWithInt(lineNumberForOffset(text, charNumber));
      }
    }
    return returnValue;
  }
}
