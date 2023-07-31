class PlaceHold {
  public NSArray internal_accessibilityAttributeNames(int childID) {
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
    if ((childID == ACC.CHILDID_SELF) && (attributeNames != null)) {
      return retainedAutoreleased(attributeNames);
    }
    NSMutableArray returnValue = NSMutableArray.arrayWithCapacity(Accessible.baseAttributes.length);
    for (int i = 0; i < Accessible.baseAttributes.length; i++) {
      if (!returnValue.containsObject(baseAttributes[i])) {
        returnValue.addObject(baseAttributes[i]);
      }
    }
    switch (event.detail) {
      case ACC.ROLE_CLIENT_AREA:
        break;
      case ACC.ROLE_WINDOW:
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_MENUBAR:
        returnValue.addObject(NSAccessibilitySelectedChildrenAttribute);
        break;
      case ACC.ROLE_MENU:
        returnValue.addObject(NSAccessibilitySelectedChildrenAttribute);
        break;
      case ACC.ROLE_MENUITEM:
        break;
      case ACC.ROLE_SEPARATOR:
        returnValue.addObject(NSAccessibilityMaxValueAttribute);
        returnValue.addObject(NSAccessibilityMinValueAttribute);
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_TOOLTIP:
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_SCROLLBAR:
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_DIALOG:
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_LABEL:
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_PUSHBUTTON:
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_CHECKBUTTON:
        returnValue.addObject(NSAccessibilityValueAttribute);
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_RADIOBUTTON:
        returnValue.addObject(NSAccessibilityValueAttribute);
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_SPLITBUTTON:
        break;
      case ACC.ROLE_COMBOBOX:
        returnValue.addObject(NSAccessibilityExpandedAttribute);
        returnValue.addObject(NSAccessibilityNumberOfCharactersAttribute);
        returnValue.addObject(NSAccessibilitySelectedTextAttribute);
        returnValue.addObject(NSAccessibilitySelectedTextRangeAttribute);
        returnValue.addObject(NSAccessibilityVisibleCharacterRangeAttribute);
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_TEXT:
        returnValue.addObject(NSAccessibilityNumberOfCharactersAttribute);
        returnValue.addObject(NSAccessibilitySelectedTextAttribute);
        returnValue.addObject(NSAccessibilitySelectedTextRangeAttribute);
        returnValue.addObject(NSAccessibilityInsertionPointLineNumberAttribute);
        returnValue.addObject(NSAccessibilitySelectedTextRangesAttribute);
        returnValue.addObject(NSAccessibilityVisibleCharacterRangeAttribute);
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_TOOLBAR:
        break;
      case ACC.ROLE_LIST:
        returnValue.addObject(NSAccessibilityColumnsAttribute);
        returnValue.addObject(NSAccessibilitySelectedColumnsAttribute);
        returnValue.addObject(NSAccessibilityRowsAttribute);
        returnValue.addObject(NSAccessibilitySelectedRowsAttribute);
        returnValue.addObject(NSAccessibilityHeaderAttribute);
        returnValue.addObject(NSAccessibilityVisibleRowsAttribute);
        returnValue.addObject(NSAccessibilityVisibleColumnsAttribute);
        break;
      case ACC.ROLE_LISTITEM:
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_TABLE:
        returnValue.addObject(NSAccessibilityColumnsAttribute);
        returnValue.addObject(NSAccessibilitySelectedColumnsAttribute);
        returnValue.addObject(NSAccessibilityRowsAttribute);
        returnValue.addObject(NSAccessibilitySelectedRowsAttribute);
        returnValue.addObject(NSAccessibilityVisibleRowsAttribute);
        returnValue.addObject(NSAccessibilityVisibleColumnsAttribute);
        returnValue.addObject(NSAccessibilityHeaderAttribute);
        break;
      case ACC.ROLE_TABLECELL:
        returnValue.addObject(NSAccessibilitySelectedAttribute);
        if (OS.VERSION >= 0x1060) {
          returnValue.addObject(NSAccessibilityRowIndexRangeAttribute);
        }
        if (OS.VERSION >= 0x1060) {
          returnValue.addObject(NSAccessibilityColumnIndexRangeAttribute);
        }
        break;
      case ACC.ROLE_TABLEROWHEADER:
        returnValue.addObject(NSAccessibilitySelectedAttribute);
        returnValue.addObject(NSAccessibilityIndexAttribute);
        break;
      case ACC.ROLE_TREE:
        returnValue.addObject(NSAccessibilityColumnsAttribute);
        returnValue.addObject(NSAccessibilitySelectedColumnsAttribute);
        returnValue.addObject(NSAccessibilityRowsAttribute);
        returnValue.addObject(NSAccessibilitySelectedRowsAttribute);
        returnValue.addObject(NSAccessibilityHeaderAttribute);
        returnValue.addObject(NSAccessibilityVisibleRowsAttribute);
        returnValue.addObject(NSAccessibilityVisibleColumnsAttribute);
        break;
      case ACC.ROLE_TREEITEM:
        returnValue.addObject(NSAccessibilityColumnsAttribute);
        returnValue.addObject(NSAccessibilitySelectedColumnsAttribute);
        returnValue.addObject(NSAccessibilityRowsAttribute);
        returnValue.addObject(NSAccessibilitySelectedRowsAttribute);
        returnValue.addObject(NSAccessibilityHeaderAttribute);
        returnValue.addObject(NSAccessibilityVisibleRowsAttribute);
        returnValue.addObject(NSAccessibilityVisibleColumnsAttribute);
      case ACC.ROLE_TABFOLDER:
        returnValue.addObject(NSAccessibilityValueAttribute);
        returnValue.addObject(NSAccessibilityContentsAttribute);
        returnValue.addObject(NSAccessibilityTabsAttribute);
        break;
      case ACC.ROLE_TABITEM:
        returnValue.addObject(NSAccessibilityValueAttribute);
        returnValue.addObject(NSAccessibilityTitleAttribute);
        break;
      case ACC.ROLE_PROGRESSBAR:
        returnValue.addObject(NSAccessibilityMaxValueAttribute);
        returnValue.addObject(NSAccessibilityMinValueAttribute);
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_SLIDER:
        returnValue.addObject(NSAccessibilityMaxValueAttribute);
        returnValue.addObject(NSAccessibilityMinValueAttribute);
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_LINK:
        break;
      case ACC.ROLE_ALERT:
        break;
      case ACC.ROLE_ANIMATION:
        break;
      case ACC.ROLE_CANVAS:
        break;
      case ACC.ROLE_COLUMN:
        returnValue.addObject(NSAccessibilityIndexAttribute);
        returnValue.addObject(NSAccessibilitySelectedAttribute);
        returnValue.addObject(NSAccessibilityRowsAttribute);
        returnValue.addObject(NSAccessibilityVisibleRowsAttribute);
        returnValue.addObject(NSAccessibilityHeaderAttribute);
        break;
      case ACC.ROLE_DOCUMENT:
        break;
      case ACC.ROLE_GRAPHIC:
        break;
      case ACC.ROLE_GROUP:
        break;
      case ACC.ROLE_HEADING:
        break;
      case ACC.ROLE_ROW:
        returnValue.addObject(NSAccessibilityIndexAttribute);
        returnValue.addObject(NSAccessibilitySelectedAttribute);
        break;
      case ACC.ROLE_SPINBUTTON:
        break;
      case ACC.ROLE_STATUSBAR:
        break;
      case ACC.ROLE_CHECKMENUITEM:
        break;
      case ACC.ROLE_RADIOMENUITEM:
        break;
      case ACC.ROLE_CLOCK:
        break;
      case ACC.ROLE_DATE_EDITOR:
        break;
      case ACC.ROLE_COLOR_CHOOSER:
        returnValue.addObject(NSAccessibilityValueAttribute);
        break;
      case ACC.ROLE_FILE_CHOOSER:
        break;
      case ACC.ROLE_FONT_CHOOSER:
        break;
    }
    if (event.detail != (-1)) {
      String osRole = roleToOs(event.detail);
      if (osRole.indexOf(':') == (-1)) {
        returnValue.removeObject(NSAccessibilitySubroleAttribute);
      }
    }
    if (childID != ACC.CHILDID_SELF) {
      returnValue.removeObject(NSAccessibilityChildrenAttribute);
    }
    if (childID == ACC.CHILDID_SELF) {
      attributeNames = returnValue;
      attributeNames.retain();
      return retainedAutoreleased(attributeNames);
    } else {
      return returnValue;
    }
  }
}
