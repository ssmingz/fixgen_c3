class PlaceHold {
  Rectangle getBounds() {
    checkWidget();
    if (OS.IsWinCE) {
      return new Rectangle(0, 0, 0, 0);
    }
    if ((style & SWT.BAR) != 0) {
      if (parent.menuBar != this) {
        return new Rectangle(0, 0, 0, 0);
      }
      int hwndShell = parent.handle;
      MENUBARINFO info = new MENUBARINFO();
      info.cbSize = MENUBARINFO.sizeof;
      if (OS.GetMenuBarInfo(hwndShell, OBJID_MENU, 0, info)) {
        int width = info.right - info.left;
        int height = info.bottom - info.top;
        return new Rectangle(info.left, info.top, width, height);
      }
    } else {
      int count = GetMenuItemCount(handle);
      if (count != 0) {
        RECT rect1 = new RECT();
        if (OS.GetMenuItemRect(0, handle, 0, rect1)) {
          RECT rect2 = new RECT();
          if (OS.GetMenuItemRect(0, handle, count - 1, rect2)) {
            int x = rect1.left - 2;
            int y = rect1.top - 2;
            int width = (rect2.right - rect2.left) + 4;
            int height = (rect2.bottom - rect1.top) + 4;
            return new Rectangle(x, y, width, height);
          }
        }
      }
    }
    return new Rectangle(0, 0, 0, 0);
  }
}
