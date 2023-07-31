class PlaceHold {
  id getRangeForLineParameterizedAttribute(id parameter, int childID) {
    id returnValue = null;
    NSNumber lineNumberObj = new NSNumber(parameter.id);
    int lineNumber = lineNumberObj.intValue();
    if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      event.start = event.end = 0;
      event.count = lineNumber;
      event.type = ACC.TEXT_BOUNDARY_LINE;
      for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
        AccessibleTextExtendedListener listener = accessibleTextExtendedListeners.get(i);
        listener.getText(event);
      }
      NSRange range = new NSRange();
      range.location = event.start;
      range.length = event.end - event.start;
      returnValue = NSValue.valueWithRange(range);
    } else if (accessibleControlListenersSize() > 0) {
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = childID;
      event.result = null;
      for (int i = 0; i < accessibleControlListenersSize(); i++) {
        AccessibleControlListener listener = accessibleControlListeners.get(i);
        listener.getValue(event);
      }
      if (event.result != null) {
        NSRange range = rangeForLineNumber(lineNumber, event.result);
        if (range.location != (-1)) {
          returnValue = NSValue.valueWithRange(range);
        }
      }
    }
    return returnValue;
  }
}
