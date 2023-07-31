class PlaceHold {
  public Point getSize() {
    checkWidget();
    if ((style & SWT.BAR) != 0) {
      MENUBARINFO info = new MENUBARINFO();
      info.cbSize = MENUBARINFO.sizeof;
      int hwndParent = parent.handle;
      if (OS.GetMenuBarInfo(hwndParent, OBJID_MENU, 0, info)) {
        int width = info.right - info.left;
        int height = info.bottom - info.top;
        return new Point(width, height);
      }
    } else {
      int count = GetMenuItemCount(handle);
      if (count != 0) {
        RECT rect = new RECT();
        int hwndParent = parent.handle;
        if (OS.GetMenuItemRect(hwndParent, handle, count - 1, rect)) {
          int width = rect.right + 4;
          int height = rect.bottom + 4;
          return new Point(width, height);
        }
      }
    }
    return new Point(0, 0);
  }
}
