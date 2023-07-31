class PlaceHold {
  int getRoleAttribute(int nextHandler, int theEvent, int userData) {
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.detail = -1;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getRole(event);
    }
    if (event.detail != (-1)) {
      String appRole = roleToOs(event.detail);
      int index = appRole.indexOf(':');
      if (index != (-1)) {
        appRole = appRole.substring(0, index);
      }
      int stringRef = stringToStringRef(appRole);
      if (stringRef != 0) {
        OS.SetEventParameter(
            theEvent,
            kEventParamAccessibleAttributeValue,
            typeCFStringRef,
            4,
            new int[] {stringRef});
        OS.CFRelease(stringRef);
        return OS.noErr;
      }
    }
    return OS.eventNotHandledErr;
  }
}
