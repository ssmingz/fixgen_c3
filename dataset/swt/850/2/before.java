class PlaceHold {
  LRESULT WM_MOUSEMOVE(int wParam, int lParam) {
    LRESULT result = super.WM_MOUSEMOVE(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((itemToolTipHandle != 0) && (hwndHeader != 0)) {
      int mask =
          (((OS.MK_LBUTTON | OS.MK_MBUTTON) | OS.MK_RBUTTON) | OS.MK_XBUTTON1) | OS.MK_XBUTTON2;
      if (((wParam & 0xffff) & mask) == 0) {
        TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = ((short) (lParam & 0xffff));
        lpht.y = ((short) (lParam >> 16));
        OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
        if (lpht.hItem != 0) {
          int hDC = OS.GetDC(handle);
          int oldFont = 0;
          int newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
          if (newFont != 0) {
            oldFont = OS.SelectObject(hDC, newFont);
          }
          POINT pt = new POINT();
          pt.x = lpht.x;
          pt.y = lpht.y;
          RECT rect = new RECT();
          OS.GetClientRect(hwndParent, rect);
          OS.MapWindowPoints(hwndParent, handle, rect, 2);
          TreeItem item = _getItem(lpht.hItem);
          int index = 0;
          int count = Math.max(1, OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0));
          int[] order = new int[count];
          OS.SendMessage(hwndHeader, HDM_GETORDERARRAY, count, order);
          while (index < count) {
            int hFont = (item.cellFont != null) ? item.cellFont[order[index]] : -1;
            if (hFont == (-1)) {
              hFont = item.font;
            }
            if (hFont != (-1)) {
              hFont = OS.SelectObject(hDC, hFont);
            }
            RECT cellRect = item.getBounds(order[index], true, false, true, false, true, hDC);
            if (hFont != (-1)) {
              OS.SelectObject(hDC, hFont);
            }
            if (cellRect.left > rect.right) {
              break;
            }
            cellRect.right = Math.min(cellRect.right, rect.right);
            if (OS.PtInRect(cellRect, pt)) {
              RECT textRect = item.getBounds(order[index], true, false, false, false, false, hDC);
              if (textRect.right > cellRect.right) {
                TOOLINFO lpti = new TOOLINFO();
                lpti.cbSize = TOOLINFO.sizeof;
                lpti.hwnd = handle;
                lpti.uId = handle;
                lpti.uFlags = OS.TTF_SUBCLASS | OS.TTF_TRANSPARENT;
                lpti.left = cellRect.left;
                lpti.top = cellRect.top;
                lpti.right = cellRect.right;
                lpti.bottom = cellRect.bottom;
                OS.SendMessage(itemToolTipHandle, TTM_NEWTOOLRECT, 0, lpti);
              }
              break;
            }
            index++;
          }
          if (newFont != 0) {
            OS.SelectObject(hDC, oldFont);
          }
          OS.ReleaseDC(handle, hDC);
        }
      }
    }
    return result;
  }
}
