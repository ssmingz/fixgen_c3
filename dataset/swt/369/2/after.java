class PlaceHold {
  String roleToOs(int role) {
    NSString nsReturnValue = null;
    switch (role) {
      case ACC.ROLE_CLIENT_AREA:
        nsReturnValue = OS.NSAccessibilityGroupRole;
        break;
      case ACC.ROLE_WINDOW:
        nsReturnValue = OS.NSAccessibilityWindowRole;
        break;
      case ACC.ROLE_MENUBAR:
        nsReturnValue = OS.NSAccessibilityMenuBarRole;
        break;
      case ACC.ROLE_MENU:
        nsReturnValue = OS.NSAccessibilityMenuRole;
        break;
      case ACC.ROLE_MENUITEM:
        nsReturnValue = OS.NSAccessibilityMenuItemRole;
        break;
      case ACC.ROLE_SEPARATOR:
        nsReturnValue = OS.NSAccessibilitySplitterRole;
        break;
      case ACC.ROLE_TOOLTIP:
        nsReturnValue = OS.NSAccessibilityHelpTagRole;
        break;
      case ACC.ROLE_SCROLLBAR:
        nsReturnValue = OS.NSAccessibilityScrollBarRole;
        break;
      case ACC.ROLE_DIALOG:
        nsReturnValue =
            concatStringsAsRole(NSAccessibilityWindowRole, NSAccessibilityDialogSubrole);
        break;
      case ACC.ROLE_LABEL:
        nsReturnValue = OS.NSAccessibilityStaticTextRole;
        break;
      case ACC.ROLE_PUSHBUTTON:
        nsReturnValue = OS.NSAccessibilityButtonRole;
        break;
      case ACC.ROLE_CHECKBUTTON:
        nsReturnValue = OS.NSAccessibilityCheckBoxRole;
        break;
      case ACC.ROLE_RADIOBUTTON:
        nsReturnValue = OS.NSAccessibilityRadioButtonRole;
        break;
      case ACC.ROLE_SPLITBUTTON:
        nsReturnValue = OS.NSAccessibilityMenuButtonRole;
        break;
      case ACC.ROLE_COMBOBOX:
        nsReturnValue = OS.NSAccessibilityComboBoxRole;
        break;
      case ACC.ROLE_TEXT:
        {
          int style = control.getStyle();
          if ((style & SWT.MULTI) != 0) {
            nsReturnValue = OS.NSAccessibilityTextAreaRole;
          } else {
            nsReturnValue = OS.NSAccessibilityTextFieldRole;
          }
          break;
        }
      case ACC.ROLE_TOOLBAR:
        nsReturnValue = OS.NSAccessibilityToolbarRole;
        break;
      case ACC.ROLE_LIST:
        nsReturnValue = OS.NSAccessibilityOutlineRole;
        break;
      case ACC.ROLE_LISTITEM:
        nsReturnValue = OS.NSAccessibilityStaticTextRole;
        break;
      case ACC.ROLE_COLUMN:
        nsReturnValue = OS.NSAccessibilityColumnRole;
        break;
      case ACC.ROLE_ROW:
        nsReturnValue = concatStringsAsRole(NSAccessibilityRowRole, NSAccessibilityTableRowSubrole);
        break;
      case ACC.ROLE_TABLE:
        nsReturnValue = OS.NSAccessibilityTableRole;
        break;
      case ACC.ROLE_TABLECELL:
        if (OS.VERSION >= 0x1060) {
          nsReturnValue = OS.NSAccessibilityCellRole;
        } else {
          nsReturnValue =
              concatStringsAsRole(NSAccessibilityRowRole, NSAccessibilityTableRowSubrole);
        }
      case ACC.ROLE_TABLECOLUMNHEADER:
        nsReturnValue = OS.NSAccessibilitySortButtonRole;
        break;
      case ACC.ROLE_TABLEROWHEADER:
        nsReturnValue = concatStringsAsRole(NSAccessibilityRowRole, NSAccessibilityTableRowSubrole);
        break;
      case ACC.ROLE_TREE:
        nsReturnValue = OS.NSAccessibilityOutlineRole;
        break;
      case ACC.ROLE_TREEITEM:
        nsReturnValue =
            concatStringsAsRole(NSAccessibilityOutlineRole, NSAccessibilityOutlineRowSubrole);
        break;
      case ACC.ROLE_TABFOLDER:
        nsReturnValue = OS.NSAccessibilityTabGroupRole;
        break;
      case ACC.ROLE_TABITEM:
        nsReturnValue = OS.NSAccessibilityRadioButtonRole;
        break;
      case ACC.ROLE_PROGRESSBAR:
        nsReturnValue = OS.NSAccessibilityProgressIndicatorRole;
        break;
      case ACC.ROLE_SLIDER:
        nsReturnValue = OS.NSAccessibilitySliderRole;
        break;
      case ACC.ROLE_LINK:
        nsReturnValue = OS.NSAccessibilityLinkRole;
        break;
      case ACC.ROLE_CANVAS:
        nsReturnValue = OS.NSAccessibilityGroupRole;
        break;
      case ACC.ROLE_GRAPHIC:
        nsReturnValue = OS.NSAccessibilityImageRole;
        break;
      case ACC.ROLE_COLOR_CHOOSER:
        nsReturnValue = OS.NSAccessibilityColorWellRole;
        break;
      case ACC.ROLE_GROUP:
        nsReturnValue = OS.NSAccessibilityGroupRole;
        break;
      case ACC.ROLE_CHECKMENUITEM:
        nsReturnValue = OS.NSAccessibilityMenuButtonRole;
        break;
      case ACC.ROLE_RADIOMENUITEM:
        nsReturnValue = OS.NSAccessibilityMenuButtonRole;
        break;
      case ACC.ROLE_ALERT:
      case ACC.ROLE_ANIMATION:
      case ACC.ROLE_DOCUMENT:
      case ACC.ROLE_HEADING:
      case ACC.ROLE_SPINBUTTON:
      case ACC.ROLE_STATUSBAR:
      case ACC.ROLE_CLOCK:
      case ACC.ROLE_DATE_EDITOR:
      case ACC.ROLE_FILE_CHOOSER:
      case ACC.ROLE_FONT_CHOOSER:
        nsReturnValue = OS.NSAccessibilityUnknownRole;
    }
    return nsReturnValue.getString();
  }
}
