class PlaceHold {
  LRESULT WM_HSCROLL(int wParam, int lParam) {
    int oldPos = 0;
    int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
    if ((bits & OS.LVS_EX_GRIDLINES) != 0) {
      SCROLLINFO info = new SCROLLINFO();
      info.cbSize = SCROLLINFO.sizeof;
      info.fMask = OS.SIF_POS;
      OS.GetScrollInfo(handle, SB_HORZ, info);
      oldPos = info.nPos;
    }
    int oldHeaderProc = 0;
    int oldTableProc = 0;
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    boolean fixSubclass = isOptimizedRedraw();
    if (fixSubclass) {
      oldTableProc = OS.SetWindowLongPtr(handle, GWLP_WNDPROC, TableProc);
      oldHeaderProc = OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, HeaderProc);
    }
    boolean fixScroll = false;
    if (OS.LOWORD(wParam) != OS.SB_ENDSCROLL) {
      if (OS.COMCTL32_MAJOR >= 6) {
        if (columnCount > H_SCROLL_LIMIT) {
          int rowCount = ((int) (OS.SendMessage(handle, LVM_GETCOUNTPERPAGE, 0, 0)));
          if (rowCount > V_SCROLL_LIMIT) {
            fixScroll = getDrawing() && OS.IsWindowVisible(handle);
          }
        }
      }
    }
    if (fixScroll) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
    }
    LRESULT result = super.WM_HSCROLL(wParam, lParam);
    if (fixScroll) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
      int flags = ((OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
      OS.RedrawWindow(handle, null, 0, flags);
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
        RECT headerRect = new RECT();
        RECT rect = new RECT();
        OS.GetClientRect(handle, rect);
        boolean[] visible = new boolean[columnCount];
        for (int i = 0; i < columnCount; i++) {
          visible[i] = true;
          if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, i, headerRect) != 0) {
            OS.MapWindowPoints(hwndHeader, handle, headerRect, 2);
            visible[i] = OS.IntersectRect(headerRect, rect, headerRect);
          }
        }
        try {
          display.hwndParent = OS.GetParent(handle);
          display.columnVisible = visible;
          OS.UpdateWindow(handle);
        } finally {
          display.columnVisible = null;
        }
      }
    }
    if (fixSubclass) {
      OS.SetWindowLongPtr(handle, GWLP_WNDPROC, oldTableProc);
      OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, oldHeaderProc);
    }
    if ((bits & OS.LVS_EX_GRIDLINES) != 0) {
      SCROLLINFO info = new SCROLLINFO();
      info.cbSize = SCROLLINFO.sizeof;
      info.fMask = OS.SIF_POS;
      OS.GetScrollInfo(handle, SB_HORZ, info);
      int newPos = info.nPos;
      if (newPos < oldPos) {
        RECT rect = new RECT();
        OS.GetClientRect(handle, rect);
        rect.right = (oldPos - newPos) + GRID_WIDTH;
        OS.InvalidateRect(handle, rect, true);
      }
    }
    return result;
  }
}
