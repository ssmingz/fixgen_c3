class PlaceHold {
  RECT getBounds(
      int row,
      int column,
      boolean getText,
      boolean getImage,
      boolean fullText,
      boolean fullImage,
      int hDC) {
    if ((!getText) && (!getImage)) {
      return new RECT();
    }
    int columnCount = parent.getColumnCount();
    if (!((0 <= column) && (column < Math.max(1, columnCount)))) {
      return new RECT();
    }
    if (parent.fixScrollWidth) {
      parent.setScrollWidth(null, true);
    }
    RECT rect = new RECT();
    int hwnd = parent.handle;
    int bits = OS.SendMessage(hwnd, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0);
    if ((column == 0) && ((bits & OS.LVS_EX_FULLROWSELECT) == 0)) {
      if (getText && getImage) {
        rect.left = OS.LVIR_SELECTBOUNDS;
      } else {
        rect.left = (getText) ? OS.LVIR_LABEL : OS.LVIR_ICON;
      }
      if (OS.SendMessage(hwnd, LVM_GETITEMRECT, row, rect) == 0) {
        return new RECT();
      }
      if (fullText || fullImage) {
        RECT headerRect = new RECT();
        int hwndHeader = OS.SendMessage(hwnd, LVM_GETHEADER, 0, 0);
        OS.SendMessage(hwndHeader, HDM_GETITEMRECT, 0, headerRect);
        if (getText && fullText) {
          rect.right = headerRect.right;
        }
        if (getImage && fullImage) {
          rect.left = headerRect.left;
        }
      }
    } else {
      boolean hasImage =
          ((column == 0) && (image != null)) || ((images != null) && (images[column] != null));
      rect.top = column;
      if ((fullText || fullImage) || (hDC == 0)) {
        rect.left = (getText) ? OS.LVIR_LABEL : OS.LVIR_ICON;
        if (OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, row, rect) == 0) {
          return new RECT();
        }
        if (hasImage) {
          if (getText && (!getImage)) {
            RECT iconRect = new RECT();
            iconRect.top = column;
            iconRect.left = OS.LVIR_ICON;
            if (OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, row, iconRect) != 0) {
              rect.left = iconRect.right + (Table.INSET / 2);
            }
          }
        } else if (getImage && (!getText)) {
          rect.right = rect.left;
        }
      } else {
        rect.left = OS.LVIR_ICON;
        if (OS.SendMessage(hwnd, LVM_GETSUBITEMRECT, row, rect) == 0) {
          return new RECT();
        }
        if (!hasImage) {
          rect.right = rect.left;
        }
        if (getText) {
          String string = (column == 0) ? text : strings != null ? strings[column] : null;
          if (string != null) {
            RECT textRect = new RECT();
            TCHAR buffer = new TCHAR(parent.getCodePage(), string, false);
            int flags = (OS.DT_NOPREFIX | OS.DT_SINGLELINE) | OS.DT_CALCRECT;
            OS.DrawText(hDC, buffer, buffer.length(), textRect, flags);
            rect.right += ((textRect.right - textRect.left) + (Table.INSET * 3)) + 1;
          }
        }
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
