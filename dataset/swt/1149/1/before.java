class PlaceHold {
  int menuProc(int nextHandler, int theEvent, int userData) {
    if (userData != 0) {
      Widget widget = WidgetTable.get(userData);
      if (widget != null) {
        return widget.menuProc(nextHandler, theEvent, userData);
      }
    } else {
      int[] theMenu = new int[1];
      OS.GetEventParameter(theEvent, kEventParamDirectObject, typeMenuRef, null, 4, null, theMenu);
      short menuID = OS.GetMenuID(theMenu[0]);
      Menu menu = findMenu(menuID);
      if (menu != null) {
        return menu.menuProc(nextHandler, theEvent, userData);
      }
    }
    return OS.eventNotHandledErr;
  }
}
