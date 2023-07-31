class PlaceHold {
  RECT getBounds(int row, int column, boolean getText, boolean getImage, boolean full) {
    if ((!getText) && (!getImage)) {
      return new RECT();
    }
    int columnCount = Math.max(1, parent.getColumnCount());
    if ((0 > column) || (column > (columnCount - 1))) {
      return new RECT();
    }
    if (parent.fixScrollWidth) {
      parent.setScrollWidth(null, true);
    }
    RECT rect = new RECT();
    int hwnd = parent.handle;
    if ((column == 0) && (columnCount == 1)) {
      if (getText && getImage) {
        rect.left = OS.LVIR_SELECTBOUNDS;
      } else {
        rect.left = (getText) ? OS.LVIR_LABEL : OS.LVIR_ICON;
      }
      if (OS.SendMessage(hwnd, LVM_GETITEMRECT, row, rect) == 0) {
        rect.left = 0;
      }
    } else {
      rect.top = column;
      rect.left = (getText) ? OS.LVIR_LABEL : OS.LVIR_ICON;
      if (OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, row, rect) != 0) {
        if (getText && getImage) {
          if (column == 0) {
            RECT iconRect = new RECT();
            iconRect.left = OS.LVIR_ICON;
            iconRect.top = column;
            if (OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, row, iconRect) != 0) {
              rect.left = iconRect.left;
              rect.right = Math.max(rect.right, iconRect.right);
            }
          }
        } else if (column != 0) {
          if ((images != null) && (images[column] != null)) {
            if (getText) {
              RECT iconRect = new RECT();
              iconRect.left = OS.LVIR_ICON;
              iconRect.top = column;
              if (OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, row, iconRect) != 0) {
                rect.left = iconRect.right + (Table.INSET / 2);
              }
            }
          } else if (getImage && (!full)) {
            rect.right = rect.left;
          }
        }
      } else {
        rect.left = rect.top = 0;
      }
    }
    int gridWidth = (parent.getLinesVisible()) ? Table.GRID_WIDTH : 0;
    if (OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
      rect.top -= gridWidth;
    }
    if (column != 0) {
      rect.left += gridWidth;
    }
    rect.right = Math.max(rect.right, rect.left);
    rect.top += gridWidth;
    rect.bottom = Math.max(rect.bottom - gridWidth, rect.top);
    return rect;
  }
}
