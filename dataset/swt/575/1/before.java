class PlaceHold {
  public Rectangle getImageBounds(int index) {
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
    rect.left = OS.LVIR_ICON;
    OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, itemIndex, rect);
    if (index == 0) {
      rect.left -= gridWidth;
    }
    int width = Math.max(0, (rect.right - rect.left) - gridWidth);
    int height = Math.max(0, (rect.bottom - rect.top) - gridWidth);
    if (index != 0) {
      LVITEM lvItem = new LVITEM();
      lvItem.mask = OS.LVIF_IMAGE;
      lvItem.iItem = itemIndex;
      lvItem.iSubItem = index;
      OS.SendMessage(hwnd, LVM_GETITEM, 0, lvItem);
      if (lvItem.iImage < 0) {
        width = 0;
      }
    }
    if (((OS.COMCTL32_MAJOR << 16) | OS.COMCTL32_MINOR) >= ((5 << 16) | 80)) {
      rect.top -= gridWidth;
    }
    return new Rectangle(rect.left + gridWidth, rect.top + gridWidth, width, height);
  }
}
