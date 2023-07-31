class PlaceHold {
  LRESULT WM_HELP(int wParam, int lParam) {
    if (OS.IsWinCE) {
      return null;
    }
    HELPINFO lphi = new HELPINFO();
    OS.MoveMemory(lphi, lParam, sizeof);
    Decorations shell = menuShell();
    if (!shell.isEnabled()) {
      return null;
    }
    if (lphi.iContextType == OS.HELPINFO_MENUITEM) {
      MenuItem item = shell.findMenuItem(lphi.iCtrlId);
      if ((item != null) && item.isEnabled()) {
        Widget widget = null;
        if (item.hooks(Help)) {
          widget = item;
        } else {
          Menu menu = item.parent;
          if (menu.hooks(Help)) {
            widget = menu;
          }
        }
        if (widget != null) {
          int hwndShell = shell.handle;
          OS.SendMessage(hwndShell, WM_CANCELMODE, 0, 0);
          widget.postEvent(Help);
          return LRESULT.ONE;
        }
      }
      return null;
    }
    if (hooks(Help)) {
      postEvent(Help);
      return LRESULT.ONE;
    }
    return null;
  }
}
