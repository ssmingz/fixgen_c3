class PlaceHold {
  LRESULT WM_VSCROLL(int wParam, int lParam) {
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
            fixScroll = (drawCount == 0) && OS.IsWindowVisible(handle);
          }
        }
      }
    }
    if (fixScroll) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
    }
    LRESULT result = super.WM_VSCROLL(wParam, lParam);
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
    int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
    if ((bits & OS.LVS_EX_GRIDLINES) != 0) {
      int code = OS.LOWORD(wParam);
      switch (code) {
        case OS.SB_ENDSCROLL:
        case OS.SB_THUMBPOSITION:
        case OS.SB_THUMBTRACK:
        case OS.SB_TOP:
        case OS.SB_BOTTOM:
          break;
        case OS.SB_LINEDOWN:
        case OS.SB_LINEUP:
          RECT rect = new RECT();
          OS.GetWindowRect(hwndHeader, rect);
          int headerHeight = rect.bottom - rect.top;
          RECT clientRect = new RECT();
          OS.GetClientRect(handle, clientRect);
          clientRect.top += headerHeight;
          int empty = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 0, 0);
          int oneItem = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 1, 0);
          int itemHeight = OS.HIWORD(oneItem) - OS.HIWORD(empty);
          if (code == OS.SB_LINEDOWN) {
            clientRect.top = (clientRect.bottom - itemHeight) - GRID_WIDTH;
          } else {
            clientRect.bottom = (clientRect.top + itemHeight) + GRID_WIDTH;
          }
          OS.InvalidateRect(handle, clientRect, true);
          break;
        case OS.SB_PAGEDOWN:
        case OS.SB_PAGEUP:
          OS.InvalidateRect(handle, null, true);
          break;
      }
    }
    return result;
  }
}
