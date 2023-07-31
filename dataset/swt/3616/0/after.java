class PlaceHold {
  int getDescriptionAttribute(int nextHandler, int theEvent, int userData) {
    int code =
        (userData != OS.eventNotHandledErr)
            ? userData
            : OS.CallNextEventHandler(nextHandler, theEvent);
    String osDescriptionAttribute = null;
    int[] stringRef = new int[1];
    if (code == OS.noErr) {
      int status =
          OS.GetEventParameter(
              theEvent,
              kEventParamAccessibleAttributeValue,
              typeCFStringRef,
              null,
              4,
              null,
              stringRef);
      if (status == OS.noErr) {
        osDescriptionAttribute = stringRefToString(stringRef[0]);
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.result = osDescriptionAttribute;
    for (int i = 0; i < accessibleListeners.size(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getDescription(event);
    }
    if (event.result != null) {
      stringRef[0] = stringToStringRef(event.result);
      if (stringRef[0] != 0) {
        OS.SetEventParameter(
            theEvent, kEventParamAccessibleAttributeValue, typeCFStringRef, 4, stringRef);
        OS.CFRelease(stringRef[0]);
        code = OS.noErr;
      }
    }
    return code;
  }
}
