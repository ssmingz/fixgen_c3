class PlaceHold {
  int getSubroleAttribute(int nextHandler, int theEvent, int userData) {
    int code = userData;
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
        appRole = appRole.substring(index + 1);
        int stringRef = stringToStringRef(appRole);
        if (stringRef != 0) {
          OS.SetEventParameter(
              theEvent,
              kEventParamAccessibleAttributeValue,
              typeCFStringRef,
              4,
              new int[] {stringRef});
          OS.CFRelease(stringRef);
        }
      }
      code = OS.noErr;
    }
    return code;
  }
}
