class PlaceHold {
  int getStringForRangeAttribute(int nextHandler, int theEvent, int userData) {
    int code = userData;
    int valueRef[] = new int[1];
    int status =
        OS.GetEventParameter(
            theEvent,
            kEventParamAccessibleAttributeParameter,
            typeCFTypeRef,
            null,
            4,
            null,
            valueRef);
    if (status == OS.noErr) {
      CFRange range = new CFRange();
      boolean ok = OS.AXValueGetValue(valueRef[0], kAXValueCFRangeType, range);
      if (ok) {
        AccessibleControlEvent event = new AccessibleControlEvent(this);
        event.childID = getChildIDFromEvent(theEvent);
        event.result = null;
        for (int i = 0; i < accessibleControlListeners.size(); i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
          listener.getValue(event);
        }
        String appValue = event.result;
        if (appValue != null) {
          int stringRef =
              stringToStringRef(appValue.substring(range.location, range.location + range.length));
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
    }
    return code;
  }
}
