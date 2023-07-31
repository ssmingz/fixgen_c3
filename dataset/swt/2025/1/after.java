class PlaceHold {
  LRESULT CDDS_POSTPAINT(NMTVCUSTOMDRAW nmcd, int wParam, int lParam) {
    if (ignoreCustomDraw) {
      return null;
    }
    if (OS.IsWindowVisible(handle)) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        if ((sortColumn != null) && (sortDirection != SWT.NONE)) {
          if (findImageControl() == null) {
            int index = indexOf(sortColumn);
            if (index != (-1)) {
              int top = nmcd.top;
              int hItem = 0;
              if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
                hItem = getBottomItem();
              } else {
                hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_LASTVISIBLE, 0);
              }
              if (hItem != 0) {
                RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(handle, hItem, rect, false)) {
                  top = rect.bottom;
                }
              }
              RECT rect = new RECT();
              OS.SetRect(rect, nmcd.left, top, nmcd.right, nmcd.bottom);
              RECT headerRect = new RECT();
              OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect);
              rect.left = headerRect.left;
              rect.right = headerRect.right;
              fillBackground(nmcd.hdc, getSortColumnPixel(), rect);
            }
          }
        }
      }
      if (linesVisible) {
        int hDC = nmcd.hdc;
        if (hwndHeader != 0) {
          int x = 0;
          RECT rect = new RECT();
          HDITEM hdItem = new HDITEM();
          hdItem.mask = OS.HDI_WIDTH;
          for (int i = 0; i < columnCount; i++) {
            int index = ((int) (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, i, 0)));
            OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
            OS.SetRect(rect, x, nmcd.top, x + hdItem.cxy, nmcd.bottom);
            OS.DrawEdge(hDC, rect, BDR_SUNKENINNER, BF_RIGHT);
            x += hdItem.cxy;
          }
        }
        int height = 0;
        RECT rect = new RECT();
        int hItem = 0;
        if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
          hItem = getBottomItem();
        } else {
          hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_LASTVISIBLE, 0);
        }
        if (hItem != 0) {
          if (OS.TreeView_GetItemRect(handle, hItem, rect, false)) {
            height = rect.bottom - rect.top;
          }
        }
        if (height == 0) {
          height = ((int) (OS.SendMessage(handle, TVM_GETITEMHEIGHT, 0, 0)));
          OS.GetClientRect(handle, rect);
          OS.SetRect(rect, rect.left, rect.top, rect.right, rect.top + height);
          OS.DrawEdge(hDC, rect, BDR_SUNKENINNER, BF_BOTTOM);
        }
        while (rect.bottom < nmcd.bottom) {
          int top = rect.top + height;
          OS.SetRect(rect, rect.left, top, rect.right, top + height);
          OS.DrawEdge(hDC, rect, BDR_SUNKENINNER, BF_BOTTOM);
        }
      }
    }
    return new LRESULT(OS.CDRF_DODEFAULT);
  }
}
