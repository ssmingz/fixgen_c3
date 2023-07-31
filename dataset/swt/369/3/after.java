class PlaceHold {
  int osToRole(NSString osRole) {
    if (osRole == null) {
      return 0;
    }
    if (osRole.isEqualToString(NSAccessibilityWindowRole)) {
      return ACC.ROLE_WINDOW;
    }
    if (osRole.isEqualToString(NSAccessibilityMenuBarRole)) {
      return ACC.ROLE_MENUBAR;
    }
    if (osRole.isEqualToString(NSAccessibilityMenuRole)) {
      return ACC.ROLE_MENU;
    }
    if (osRole.isEqualToString(NSAccessibilityMenuItemRole)) {
      return ACC.ROLE_MENUITEM;
    }
    if (osRole.isEqualToString(NSAccessibilitySplitterRole)) {
      return ACC.ROLE_SEPARATOR;
    }
    if (osRole.isEqualToString(NSAccessibilityHelpTagRole)) {
      return ACC.ROLE_TOOLTIP;
    }
    if (osRole.isEqualToString(NSAccessibilityScrollBarRole)) {
      return ACC.ROLE_SCROLLBAR;
    }
    if (osRole.isEqualToString(NSAccessibilityScrollAreaRole)) {
      return ACC.ROLE_LIST;
    }
    if (osRole.isEqualToString(
        concatStringsAsRole(NSAccessibilityWindowRole, NSAccessibilityDialogSubrole))) {
      return ACC.ROLE_DIALOG;
    }
    if (osRole.isEqualToString(
        concatStringsAsRole(NSAccessibilityWindowRole, NSAccessibilitySystemDialogSubrole))) {
      return ACC.ROLE_DIALOG;
    }
    if (osRole.isEqualToString(NSAccessibilityStaticTextRole)) {
      return ACC.ROLE_LABEL;
    }
    if (osRole.isEqualToString(NSAccessibilityButtonRole)) {
      return ACC.ROLE_PUSHBUTTON;
    }
    if (osRole.isEqualToString(NSAccessibilityCheckBoxRole)) {
      return ACC.ROLE_CHECKBUTTON;
    }
    if (osRole.isEqualToString(NSAccessibilityRadioButtonRole)) {
      return ACC.ROLE_RADIOBUTTON;
    }
    if (osRole.isEqualToString(NSAccessibilityMenuButtonRole)) {
      return ACC.ROLE_SPLITBUTTON;
    }
    if (osRole.isEqualToString(NSAccessibilityComboBoxRole)) {
      return ACC.ROLE_COMBOBOX;
    }
    if (osRole.isEqualToString(NSAccessibilityTextFieldRole)) {
      return ACC.ROLE_TEXT;
    }
    if (osRole.isEqualToString(NSAccessibilityTextAreaRole)) {
      return ACC.ROLE_TEXT;
    }
    if (osRole.isEqualToString(NSAccessibilityToolbarRole)) {
      return ACC.ROLE_TOOLBAR;
    }
    if (osRole.isEqualToString(NSAccessibilityListRole)) {
      return ACC.ROLE_LIST;
    }
    if (osRole.isEqualToString(NSAccessibilityTableRole)) {
      return ACC.ROLE_TABLE;
    }
    if (osRole.isEqualToString(NSAccessibilityColumnRole)) {
      return ACC.ROLE_COLUMN;
    }
    if (osRole.isEqualToString(
        concatStringsAsRole(NSAccessibilityButtonRole, NSAccessibilitySortButtonRole))) {
      return ACC.ROLE_TABLECOLUMNHEADER;
    }
    if (osRole.isEqualToString(
        concatStringsAsRole(NSAccessibilityRowRole, NSAccessibilityTableRowSubrole))) {
      return ACC.ROLE_ROW;
    }
    if (osRole.isEqualToString(NSAccessibilityOutlineRole)) {
      return ACC.ROLE_TREE;
    }
    if (osRole.isEqualToString(
        concatStringsAsRole(NSAccessibilityOutlineRole, NSAccessibilityOutlineRowSubrole))) {
      return ACC.ROLE_TREEITEM;
    }
    if (osRole.isEqualToString(NSAccessibilityTabGroupRole)) {
      return ACC.ROLE_TABFOLDER;
    }
    if (osRole.isEqualToString(NSAccessibilityProgressIndicatorRole)) {
      return ACC.ROLE_PROGRESSBAR;
    }
    if (osRole.isEqualToString(NSAccessibilitySliderRole)) {
      return ACC.ROLE_SLIDER;
    }
    if (osRole.isEqualToString(NSAccessibilityLinkRole)) {
      return ACC.ROLE_LINK;
    }
    if (osRole.isEqualToString(NSAccessibilityGroupRole)) {
      return ACC.ROLE_CANVAS;
    }
    if (osRole.isEqualToString(NSAccessibilityGroupRole)) {
      return ACC.ROLE_GROUP;
    }
    if (osRole.isEqualToString(NSAccessibilityImageRole)) {
      return ACC.ROLE_GRAPHIC;
    }
    if (osRole.isEqualToString(NSAccessibilityMenuButtonRole)) {
      return ACC.ROLE_CHECKMENUITEM;
    }
    if (osRole.isEqualToString(NSAccessibilityMenuButtonRole)) {
      return ACC.ROLE_RADIOMENUITEM;
    }
    if (osRole.isEqualToString(NSAccessibilityColorWellRole)) {
      return ACC.ROLE_COLOR_CHOOSER;
    }
    if ((OS.VERSION >= 0x1060) && osRole.isEqualToString(NSAccessibilityCellRole)) {
      return ACC.ROLE_TABLECELL;
    }
    return ACC.ROLE_CLIENT_AREA;
  }
}
