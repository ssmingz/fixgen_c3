class PlaceHold {
  public Rectangle getBounds(int index) {
    checkWidget();
    int itemIndex = parent.indexOf(this);
    if (itemIndex == (-1)) {
      return new Rectangle(0, 0, 0, 0);
    }
    int hwnd = parent.handle;
    int hwndHeader = OS.SendMessage(hwnd, LVM_GETHEADER, 0, 0);
    int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    if (!((0 <= index) && (index < count))) {
      return new Rectangle(0, 0, 0, 0);
    }
    int gridWidth = (parent.getLinesVisible()) ? parent.getGridLineWidth() : 0;
    RECT rect = new RECT();
    rect.top = index;
    rect.left = OS.LVIR_LABEL;
    OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, itemIndex, rect);
    if (index == 0) {
      RECT iconRect = new RECT();
      iconRect.left = OS.LVIR_ICON;
      OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, itemIndex, iconRect);
      rect.left = iconRect.left - gridWidth;
    }
    int width = Math.max(0, (rect.right - rect.left) - gridWidth);
    int height = Math.max(0, (rect.bottom - rect.top) - gridWidth);
    if (OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
      rect.top -= gridWidth;
    }
    return new Rectangle(rect.left + gridWidth, rect.top + gridWidth, width, height);
  }
}
