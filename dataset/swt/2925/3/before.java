class PlaceHold {
  static int atkObject_get_role(int atkObject) {
    if (DEBUG) {
      print("-->atkObject_get_role: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleControlEvent event = new AccessibleControlEvent(object.accessible);
        event.childID = object.id;
        event.detail = -1;
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (listeners.elementAt(i)));
          listener.getRole(event);
        }
        if (DEBUG) {
          print("---> " + event.detail);
        }
        if (event.detail != (-1)) {
          switch (event.detail) {
            case ACC.ROLE_CHECKBUTTON:
              return ATK.ATK_ROLE_CHECK_BOX;
            case ACC.ROLE_CLIENT_AREA:
              return ATK.ATK_ROLE_DRAWING_AREA;
            case ACC.ROLE_COMBOBOX:
              return ATK.ATK_ROLE_COMBO_BOX;
            case ACC.ROLE_DIALOG:
              return ATK.ATK_ROLE_DIALOG;
            case ACC.ROLE_LABEL:
              return ATK.ATK_ROLE_LABEL;
            case ACC.ROLE_LINK:
              return ATK.ATK_ROLE_TEXT;
            case ACC.ROLE_LIST:
              return ATK.ATK_ROLE_LIST;
            case ACC.ROLE_LISTITEM:
              return ATK.ATK_ROLE_LIST_ITEM;
            case ACC.ROLE_MENU:
              return ATK.ATK_ROLE_MENU;
            case ACC.ROLE_MENUBAR:
              return ATK.ATK_ROLE_MENU_BAR;
            case ACC.ROLE_MENUITEM:
              return ATK.ATK_ROLE_MENU_ITEM;
            case ACC.ROLE_PROGRESSBAR:
              return ATK.ATK_ROLE_PROGRESS_BAR;
            case ACC.ROLE_PUSHBUTTON:
              return ATK.ATK_ROLE_PUSH_BUTTON;
            case ACC.ROLE_SCROLLBAR:
              return ATK.ATK_ROLE_SCROLL_BAR;
            case ACC.ROLE_SEPARATOR:
              return ATK.ATK_ROLE_SEPARATOR;
            case ACC.ROLE_SLIDER:
              return ATK.ATK_ROLE_SLIDER;
            case ACC.ROLE_TABLE:
              return ATK.ATK_ROLE_TABLE;
            case ACC.ROLE_TABLECELL:
              return ATK.ATK_ROLE_TABLE_CELL;
            case ACC.ROLE_TABLECOLUMNHEADER:
              return ATK.ATK_ROLE_TABLE_COLUMN_HEADER;
            case ACC.ROLE_TABLEROWHEADER:
              return ATK.ATK_ROLE_TABLE_ROW_HEADER;
            case ACC.ROLE_TABFOLDER:
              return ATK.ATK_ROLE_PAGE_TAB_LIST;
            case ACC.ROLE_TABITEM:
              return ATK.ATK_ROLE_PAGE_TAB;
            case ACC.ROLE_TEXT:
              return ATK.ATK_ROLE_TEXT;
            case ACC.ROLE_TOOLBAR:
              return ATK.ATK_ROLE_TOOL_BAR;
            case ACC.ROLE_TOOLTIP:
              return ATK.ATK_ROLE_TOOL_TIP;
            case ACC.ROLE_TREE:
              return ATK.ATK_ROLE_TREE;
            case ACC.ROLE_TREEITEM:
              return ATK.ATK_ROLE_LIST_ITEM;
            case ACC.ROLE_RADIOBUTTON:
              return ATK.ATK_ROLE_RADIO_BUTTON;
            case ACC.ROLE_SPLITBUTTON:
              return ATK.ATK_ROLE_PUSH_BUTTON;
            case ACC.ROLE_WINDOW:
              return ATK.ATK_ROLE_WINDOW;
            case ACC.ROLE_ROW:
              return ROW_ROLE;
            case ACC.ROLE_COLUMN:
              return COLUMN_ROLE;
            case ACC.ROLE_ALERT:
              return ATK.ATK_ROLE_ALERT;
            case ACC.ROLE_ANIMATION:
              return ATK.ATK_ROLE_ANIMATION;
            case ACC.ROLE_CANVAS:
              return ATK.ATK_ROLE_CANVAS;
            case ACC.ROLE_GROUP:
              return ATK.ATK_ROLE_PANEL;
            case ACC.ROLE_SPINBUTTON:
              return ATK.ATK_ROLE_SPIN_BUTTON;
            case ACC.ROLE_STATUSBAR:
              return ATK.ATK_ROLE_STATUSBAR;
            case ACC.ROLE_CHECKMENUITEM:
              return ATK.ATK_ROLE_CHECK_MENU_ITEM;
            case ACC.ROLE_RADIOMENUITEM:
              return ATK.ATK_ROLE_RADIO_MENU_ITEM;
          }
        }
      }
    }
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.get_role == 0) {
      return 0;
    }
    return ATK.call(objectClass.get_role, atkObject);
  }
}
