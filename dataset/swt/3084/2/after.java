class PlaceHold {
  id getStyleRangeForIndexAttribute(id parameter, int childID) {
    if (accessibleAttributeListenersSize() == 0) {
      return null;
    }
    NSNumber parameterObject = new NSNumber(parameter.id);
    int index = parameterObject.intValue();
    AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(this);
    event.offset = ((int) (index));
    event.start = event.end = -1;
    for (int i = 0; i < accessibleAttributeListenersSize(); i++) {
      AccessibleAttributeListener listener = accessibleAttributeListeners.get(i);
      listener.getTextAttributes(event);
    }
    NSRange range = new NSRange();
    if ((event.start == (-1)) && (event.end == (-1))) {
      range.location = index;
      range.length = 0;
    } else {
      range.location = event.start;
      range.length = event.end - event.start;
    }
    return NSValue.valueWithRange(range);
  }
}
