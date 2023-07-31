class PlaceHold {
  int getHelpAttribute(int nextHandler, int theEvent, int userData) {
    int code = OS.CallNextEventHandler(nextHandler, theEvent);
    String osHelpAttribute = null;
    int[] stringRef = new int[1];
    if (code == OS.noErr) {
      OS.GetEventParameter(
          theEvent, kEventParamAccessibleAttributeValue, typeCFStringRef, null, 4, null, stringRef);
      osHelpAttribute = stringRefToString(stringRef[0]);
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.result = osHelpAttribute;
    for (int i = 0; i < accessibleListeners.size(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getHelp(event);
    }
    if (event.result != null) {
      stringRef[0] = stringToStringRef(event.result);
      if (stringRef[0] != 0) {
        OS.SetEventParameter(
            theEvent, kEventParamAccessibleAttributeValue, typeCFStringRef, 4, stringRef);
        OS.CFRelease(stringRef[0]);
        return OS.noErr;
      }
    }
    return code;
  }
}
