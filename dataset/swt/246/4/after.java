class PlaceHold {
  RECT getBounds(
      int index,
      boolean getText,
      boolean getImage,
      boolean fullText,
      boolean fullImage,
      boolean clip,
      int hDC) {
    if ((!getText) && (!getImage)) {
      return new RECT();
    }
    int hwnd = parent.handle;
    if ((((parent.style & SWT.VIRTUAL) == 0) && (!cached)) && (!parent.painted)) {
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
      tvItem.hItem = handle;
      tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
      parent.ignoreCustomDraw = true;
      OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
      parent.ignoreCustomDraw = false;
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
      rect.left = handle;
      boolean full = ((((columnCount == 0) && getText) && getImage) && fullText) && fullImage;
      if (OS.SendMessage(hwnd, TVM_GETITEMRECT, full ? 0 : 1, rect) == 0) {
        return new RECT();
      }
      if (getImage && (!fullImage)) {
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
      if ((fullText || fullImage) || clip) {
        if (hwndHeader != 0) {
          RECT headerRect = new RECT();
          if (columnCount != 0) {
            if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect) == 0) {
              return new RECT();
            }
          } else {
            headerRect.right = parent.scrollWidth;
            if (headerRect.right == 0) {
              headerRect = rect;
            }
          }
          if (fullText) {
            rect.right = headerRect.right;
          }
          if (fullImage) {
            rect.left = headerRect.left;
          }
          if (clip && (headerRect.right < rect.right)) {
            rect.right = headerRect.right;
          }
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
      rect.left = handle;
      if (OS.SendMessage(hwnd, TVM_GETITEMRECT, 0, rect) == 0) {
        return new RECT();
      }
      rect.left = headerRect.left;
      if (fullText) {
        rect.right = headerRect.right;
      } else {
        rect.right = headerRect.left;
        Image image = null;
        if (index == 0) {
          image = this.image;
        } else if (images != null) {
          image = images[index];
        }
        if (image != null) {
          Point size = parent.getImageSize();
          rect.right += size.x;
        }
        if (getText) {
          String string = (index == 0) ? text : strings != null ? strings[index] : null;
          if (string != null) {
            RECT textRect = new RECT();
            TCHAR buffer = new TCHAR(parent.getCodePage(), string, false);
            int flags = (OS.DT_NOPREFIX | OS.DT_SINGLELINE) | OS.DT_CALCRECT;
            int hNewDC = hDC;
            int hFont = 0;
            if (hDC == 0) {
              hNewDC = OS.GetDC(hwnd);
              hFont = (cellFont != null) ? cellFont[index] : -1;
              if (hFont == (-1)) {
                hFont = font;
              }
              if (hFont == (-1)) {
                hFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
              }
              hFont = OS.SelectObject(hNewDC, hFont);
            }
            OS.DrawText(hNewDC, buffer, buffer.length(), textRect, flags);
            if (hDC == 0) {
              OS.SelectObject(hNewDC, hFont);
              OS.ReleaseDC(hwnd, hNewDC);
            }
            if (getImage) {
              rect.right += (textRect.right - textRect.left) + (Tree.INSET * 3);
            } else {
              rect.left = rect.right + Tree.INSET;
              rect.right = (rect.left + (textRect.right - textRect.left)) + Tree.INSET;
            }
          }
        }
        if (clip && (headerRect.right < rect.right)) {
          rect.right = headerRect.right;
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
