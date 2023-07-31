class PlaceHold {
  int getSelectedTextRangeAttribute(int nextHandler, int theEvent, int userData) {
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
    if (event.offset != (-1)) {
      CFRange range = new CFRange();
      range.location = event.offset;
      range.length = event.length;
      int valueRef = OS.AXValueCreate(kAXValueCFRangeType, range);
      OS.SetEventParameter(
          theEvent, kEventParamAccessibleAttributeValue, typeCFTypeRef, 4, new int[] {valueRef});
      OS.CFRelease(valueRef);
      code = OS.noErr;
    }
    return code;
  }
}
