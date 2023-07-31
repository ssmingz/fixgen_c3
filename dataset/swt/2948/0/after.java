class PlaceHold {
  LRESULT CDDS_PREPAINT(NMLVCUSTOMDRAW nmcd, int wParam, int lParam) {
    if (ignoreCustomDraw) {
      return new LRESULT(OS.CDRF_NOTIFYITEMDRAW | OS.CDRF_NOTIFYPOSTPAINT);
    }
    if (((customCount++) == 0) && OS.IsWindowVisible(handle)) {
      if ((!explorerTheme) && ((style & SWT.FULL_SELECTION) != 0)) {
        if (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0) == OS.CLR_NONE) {
          int dwExStyle = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
          if ((dwExStyle & OS.LVS_EX_FULLROWSELECT) != 0) {
            int bits = OS.LVS_EX_FULLROWSELECT;
            if (OS.IsWinCE) {
              RECT rect = new RECT();
              boolean damaged = OS.GetUpdateRect(handle, rect, true);
              OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, 0);
              OS.ValidateRect(handle, null);
              if (damaged) {
                OS.InvalidateRect(handle, rect, true);
              }
            } else {
              int rgn = OS.CreateRectRgn(0, 0, 0, 0);
              int result = OS.GetUpdateRgn(handle, rgn, true);
              OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, 0);
              OS.ValidateRect(handle, null);
              if (result != OS.NULLREGION) {
                OS.InvalidateRgn(handle, rgn, true);
              }
              OS.DeleteObject(rgn);
            }
          }
        }
      }
    }
    if (OS.IsWindowVisible(handle)) {
      boolean draw = true;
      if (explorerTheme && (columnCount == 0)) {
        int hDC = nmcd.hdc;
        RECT rect = new RECT();
        OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
        if (OS.IsWindowEnabled(handle) || (findImageControl() != null)) {
          drawBackground(hDC, rect);
        } else {
          fillBackground(hDC, OS.GetSysColor(COLOR_3DFACE), rect);
        }
        draw = false;
      }
      if (draw) {
        Control control = findBackgroundControl();
        if ((control != null) && (control.backgroundImage != null)) {
          RECT rect = new RECT();
          OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
          fillImageBackground(nmcd.hdc, control, rect);
        } else if (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0) == OS.CLR_NONE) {
          if (OS.IsWindowEnabled(handle)) {
            RECT rect = new RECT();
            OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
            if (control == null) {
              control = this;
            }
            fillBackground(nmcd.hdc, control.getBackgroundPixel(), rect);
            if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
              if ((sortColumn != null) && (sortDirection != SWT.NONE)) {
                int index = indexOf(sortColumn);
                if (index != (-1)) {
                  parent.forceResize();
                  int clrSortBk = getSortColumnPixel();
                  RECT columnRect = new RECT();
                  RECT headerRect = new RECT();
                  OS.GetClientRect(handle, columnRect);
                  int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
                  if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect) != 0) {
                    OS.MapWindowPoints(hwndHeader, handle, headerRect, 2);
                    columnRect.left = headerRect.left;
                    columnRect.right = headerRect.right;
                    if (OS.IntersectRect(columnRect, columnRect, rect)) {
                      fillBackground(nmcd.hdc, clrSortBk, columnRect);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return new LRESULT(OS.CDRF_NOTIFYITEMDRAW | OS.CDRF_NOTIFYPOSTPAINT);
  }
}
