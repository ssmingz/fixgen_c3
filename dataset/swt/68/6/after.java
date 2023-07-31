class PlaceHold {
  int getSelectedTextAttribute(int nextHandler, int theEvent, int userData) {
    int code = userData;
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.offset = -1;
    event.length = -1;
    for (int i = 0; i < accessibleTextListeners.size(); i++) {
      AccessibleTextListener listener =
          ((AccessibleTextListener) (accessibleTextListeners.elementAt(i)));
      listener.getSelectionRange(event);
    }
    int offset = event.offset;
    int length = event.length;
    if (((offset != (-1)) && (length != (-1))) && (length != 0)) {
      AccessibleControlEvent event2 = new AccessibleControlEvent(this);
      event2.childID = event.childID;
      event2.result = null;
      for (int i = 0; i < accessibleControlListeners.size(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getValue(event2);
      }
      String appValue = event2.result;
      if (appValue != null) {
        int stringRef = stringToStringRef(appValue.substring(offset, offset + length));
        if (stringRef != 0) {
          OS.SetEventParameter(
              theEvent,
              kEventParamAccessibleAttributeValue,
              typeCFStringRef,
              4,
              new int[] {stringRef});
          OS.CFRelease(stringRef);
          code = OS.noErr;
        }
      }
    }
    return code;
  }
}
