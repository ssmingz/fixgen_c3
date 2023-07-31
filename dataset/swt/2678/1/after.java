class PlaceHold {
  public NSArray internal_accessibilityParameterizedAttributeNames(int childID) {
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
    if ((childID == ACC.CHILDID_SELF) && (parameterizedAttributeNames != null)) {
      return retainedAutoreleased(parameterizedAttributeNames);
    }
    NSMutableArray returnValue = NSMutableArray.arrayWithCapacity(4);
    switch (event.detail) {
      case ACC.ROLE_TEXT:
      case ACC.ROLE_PARAGRAPH:
      case ACC.ROLE_HEADING:
        returnValue.addObject(NSAccessibilityStringForRangeParameterizedAttribute);
        returnValue.addObject(NSAccessibilityRangeForLineParameterizedAttribute);
        returnValue.addObject(NSAccessibilityRangeForIndexParameterizedAttribute);
        returnValue.addObject(NSAccessibilityLineForIndexParameterizedAttribute);
        returnValue.addObject(NSAccessibilityBoundsForRangeParameterizedAttribute);
        returnValue.addObject(NSAccessibilityRangeForPositionParameterizedAttribute);
        returnValue.addObject(NSAccessibilityAttributedStringForRangeParameterizedAttribute);
        returnValue.addObject(NSAccessibilityStyleRangeForIndexParameterizedAttribute);
        break;
      case ACC.ROLE_TABLE:
        if (OS.VERSION >= 0x1060) {
          returnValue.addObject(NSAccessibilityCellForColumnAndRowParameterizedAttribute);
        }
        break;
    }
    if (childID == ACC.CHILDID_SELF) {
      parameterizedAttributeNames = returnValue;
      parameterizedAttributeNames.retain();
      return retainedAutoreleased(parameterizedAttributeNames);
    } else {
      return returnValue;
    }
  }
}
