class PlaceHold {
  int getRoleDescriptionAttribute(int nextHandler, int theEvent, int userData) {
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
      String appSubrole = null;
      int index = appRole.indexOf(':');
      if (index != (-1)) {
        appSubrole = appRole.substring(index + 1);
        appRole = appRole.substring(0, index);
      }
      int stringRef1 = stringToStringRef(appRole);
      if (stringRef1 != 0) {
        int stringRef2 = 0;
        if (appSubrole != null) {
          stringRef2 = stringToStringRef(appSubrole);
        }
        int stringRef3 = OS.HICopyAccessibilityRoleDescription(stringRef1, stringRef2);
        OS.CFRelease(stringRef1);
        if (stringRef2 != 0) {
          OS.CFRelease(stringRef2);
        }
        if (stringRef3 != 0) {
          OS.SetEventParameter(
              theEvent,
              kEventParamAccessibleAttributeValue,
              typeCFStringRef,
              4,
              new int[] {stringRef3});
          OS.CFRelease(stringRef3);
          code = OS.noErr;
        }
      }
    }
    return code;
  }
}
