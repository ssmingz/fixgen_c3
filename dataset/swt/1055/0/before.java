class PlaceHold {
  RECT getBounds(int index, boolean getText, boolean getImage, boolean full) {
    if ((!getText) && (!getImage)) {
      return new RECT();
    }
    if (((parent.style & SWT.VIRTUAL) == 0) && (!cached)) {
      int hwnd = parent.handle;
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
      tvItem.hItem = handle;
      tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
      OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
    }
    boolean firstColumn = index == 0;
    int columnCount = 0;
    int hwndHeader = parent.hwndHeader;
    if (hwndHeader != 0) {
      columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
      firstColumn = index == OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0);
    }
    RECT rect = new RECT();
    if (firstColumn) {
      int hwnd = parent.handle;
      rect.left = handle;
      if (OS.SendMessage(hwnd, TVM_GETITEMRECT, 1, rect) == 0) {
        return new RECT();
      }
      if (getImage) {
        if (OS.SendMessage(hwnd, TVM_GETIMAGELIST, TVSIL_NORMAL, 0) != 0) {
          Point size = parent.getImageSize();
          rect.left -= size.x + Tree.INSET;
          if (!getText) {
            rect.right = rect.left + size.x;
          }
        } else if (!getText) {
          rect.right = rect.left;
        }
      }
      if ((getText && (hwndHeader != 0)) && (columnCount != 0)) {
        RECT headerRect = new RECT();
        if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect) == 0) {
          return new RECT();
        }
        if (full || (headerRect.right < rect.right)) {
          rect.right = headerRect.right;
        }
      }
    } else {
      if (!((0 <= index) && (index < columnCount))) {
        return new RECT();
      }
      RECT headerRect = new RECT();
      if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect) == 0) {
        return new RECT();
      }
      int hwnd = parent.handle;
      rect.left = handle;
      if (OS.SendMessage(hwnd, TVM_GETITEMRECT, 0, rect) == 0) {
        return new RECT();
      }
      rect.left = headerRect.left;
      rect.right = headerRect.right;
      if ((!getText) || (!getImage)) {
        Image image = null;
        if (index == 0) {
          image = this.image;
        } else if (images != null) {
          image = images[index];
        }
        if (image != null) {
          Point size = parent.getImageSize();
          if (getImage) {
            rect.right = Math.min(rect.left + size.x, rect.right);
          } else {
            rect.left = Math.min(rect.left + size.x, rect.right);
          }
        } else if (getImage) {
          rect.right = rect.left;
        }
      }
    }
    int gridWidth = (parent.getLinesVisible()) ? Tree.GRID_WIDTH : 0;
    if (getText || (!getImage)) {
      rect.right = Math.max(rect.left, rect.right - gridWidth);
    }
    rect.bottom = Math.max(rect.top, rect.bottom - gridWidth);
    return rect;
  }
}
