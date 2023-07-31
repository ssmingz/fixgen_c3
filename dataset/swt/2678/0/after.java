class PlaceHold {
  public NSArray internal_accessibilityActionNames(int childID) {
    if (accessibleActionListeners.size() > 0) {
      AccessibleActionEvent event = new AccessibleActionEvent(this);
      for (int i = 0; i < accessibleActionListeners.size(); i++) {
        AccessibleActionListener listener =
            ((AccessibleActionListener) (accessibleActionListeners.elementAt(i)));
        listener.getActionCount(event);
      }
      NSMutableArray array = NSMutableArray.arrayWithCapacity(event.count);
      for (int i = 0; i < event.count; i++) {
        event.index = i;
        for (int j = 0; j < accessibleActionListeners.size(); j++) {
          AccessibleActionListener listener =
              ((AccessibleActionListener) (accessibleActionListeners.elementAt(j)));
          listener.getName(event);
        }
        array.addObject(NSString.stringWith(event.result));
      }
      return array;
    } else {
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = childID;
      event.detail = -1;
      for (int i = 0; i < accessibleControlListeners.size(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getRole(event);
      }
      if (event.detail == (-1)) {
        return null;
      }
      checkRole(event.detail);
      if ((childID == ACC.CHILDID_SELF) && (actionNames != null)) {
        return retainedAutoreleased(actionNames);
      }
      NSMutableArray returnValue = NSMutableArray.arrayWithCapacity(5);
      switch (event.detail) {
        case ACC.ROLE_PUSHBUTTON:
        case ACC.ROLE_RADIOBUTTON:
        case ACC.ROLE_CHECKBUTTON:
        case ACC.ROLE_TABITEM:
        case ACC.ROLE_LINK:
        case ACC.ROLE_CHECKMENUITEM:
        case ACC.ROLE_RADIOMENUITEM:
        case ACC.ROLE_SPLITBUTTON:
          returnValue.addObject(NSAccessibilityPressAction);
          break;
        case ACC.ROLE_COMBOBOX:
          returnValue.addObject(NSAccessibilityConfirmAction);
          break;
        case ACC.ROLE_WINDOW:
        case ACC.ROLE_DIALOG:
          break;
      }
      if (childID == ACC.CHILDID_SELF) {
        actionNames = returnValue;
        actionNames.retain();
        return retainedAutoreleased(actionNames);
      } else {
        return returnValue;
      }
    }
  }
}
