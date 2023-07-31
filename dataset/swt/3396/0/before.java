class PlaceHold {
  int commandProc(int nextHandler, int theEvent, int userData) {
    int eventKind = OS.GetEventKind(theEvent);
    HICommand command = new HICommand();
    OS.GetEventParameter(
        theEvent, kEventParamDirectObject, typeHICommand, null, sizeof, null, command);
    switch (eventKind) {
      case OS.kEventProcessCommand:
        {
          if (command.commandID == OS.kAEQuitApplication) {
            close();
            return OS.noErr;
          }
          if ((command.attributes & OS.kHICommandFromMenu) != 0) {
            if (userData != 0) {
              Widget widget = getWidget(userData);
              if (widget != null) {
                return widget.commandProc(nextHandler, theEvent, userData);
              }
            } else {
              int menuRef = command.menu_menuRef;
              short menuID = OS.GetMenuID(menuRef);
              Menu menu = findMenu(menuID);
              if (menu != null) {
                MenuItem item = null;
                if (menu.closed && menu.modified) {
                  item = menu.lastTarget;
                } else {
                  item = menu.getItem(command.menu_menuItemIndex - 1);
                }
                if (item != null) {
                  return item.kEventProcessCommand(nextHandler, theEvent, userData);
                }
              }
              OS.HiliteMenu(((short) (0)));
            }
          }
        }
    }
    return OS.eventNotHandledErr;
  }
}
