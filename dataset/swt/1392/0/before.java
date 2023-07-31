class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    if ((hwndHeader != 0) && (hwnd == hwndHeader)) {
      switch (msg) {
        case OS.WM_CAPTURECHANGED:
          {
            if (OS.COMCTL32_MAJOR < 6) {
              if ((lParam != 0) && (lParam != hwndHeader)) {
                OS.InvalidateRect(hwndHeader, null, true);
              }
            }
            break;
          }
        case OS.WM_MOUSELEAVE:
          {
            if (OS.COMCTL32_MAJOR >= 6) {
              updateHeaderToolTips();
            }
            updateHeaderToolTips();
            break;
          }
        case OS.WM_NOTIFY:
          {
            NMHDR hdr = new NMHDR();
            OS.MoveMemory(hdr, lParam, sizeof);
            switch (hdr.code) {
              case OS.TTN_SHOW:
              case OS.TTN_POP:
              case OS.TTN_GETDISPINFOA:
              case OS.TTN_GETDISPINFOW:
                return OS.SendMessage(handle, msg, wParam, lParam);
            }
            break;
          }
        case OS.WM_SETCURSOR:
          {
            if (wParam == hwnd) {
              int hitTest = ((short) (OS.LOWORD(lParam)));
              if (hitTest == OS.HTCLIENT) {
                HDHITTESTINFO pinfo = new HDHITTESTINFO();
                int pos = OS.GetMessagePos();
                POINT pt = new POINT();
                OS.POINTSTOPOINT(pt, pos);
                OS.ScreenToClient(hwnd, pt);
                pinfo.x = pt.x;
                pinfo.y = pt.y;
                int columnCount = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
                int index = ((int) (OS.SendMessage(hwndHeader, HDM_HITTEST, 0, pinfo)));
                if (((0 <= index) && (index < columnCount)) && (!columns[index].resizable)) {
                  if ((pinfo.flags & (OS.HHT_ONDIVIDER | OS.HHT_ONDIVOPEN)) != 0) {
                    OS.SetCursor(OS.LoadCursor(0, IDC_ARROW));
                    return 1;
                  }
                }
              }
            }
            break;
          }
      }
      return callWindowProc(hwnd, msg, wParam, lParam);
    }
    if ((hwndParent != 0) && (hwnd == hwndParent)) {
      switch (msg) {
        case OS.WM_MOVE:
          {
            sendEvent(Move);
            return 0;
          }
        case OS.WM_SIZE:
          {
            setScrollWidth();
            if (ignoreResize) {
              return 0;
            }
            setResizeChildren(false);
            int code = callWindowProc(hwnd, WM_SIZE, wParam, lParam);
            sendEvent(Resize);
            if (isDisposed()) {
              return 0;
            }
            if (layout != null) {
              markLayout(false, false);
              updateLayout(false, false);
            }
            setResizeChildren(true);
            updateScrollBar();
            return code;
          }
        case OS.WM_NCPAINT:
          {
            LRESULT result = wmNCPaint(hwnd, wParam, lParam);
            if (result != null) {
              return result.value;
            }
            break;
          }
        case OS.WM_PRINT:
          {
            LRESULT result = wmPrint(hwnd, wParam, lParam);
            if (result != null) {
              return result.value;
            }
            break;
          }
        case OS.WM_COMMAND:
        case OS.WM_NOTIFY:
        case OS.WM_SYSCOLORCHANGE:
          {
            return OS.SendMessage(handle, msg, wParam, lParam);
          }
        case OS.WM_HSCROLL:
          {
            if ((horizontalBar != null) && ((lParam == 0) || (lParam == hwndParent))) {
              wmScroll(horizontalBar, true, hwndParent, WM_HSCROLL, wParam, lParam);
            }
            setScrollWidth();
            break;
          }
        case OS.WM_VSCROLL:
          {
            SCROLLINFO info = new SCROLLINFO();
            info.cbSize = SCROLLINFO.sizeof;
            info.fMask = OS.SIF_ALL;
            OS.GetScrollInfo(hwndParent, SB_VERT, info);
            if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
              if (OS.LOWORD(wParam) == OS.SB_THUMBTRACK) {
                info.nPos = info.nTrackPos;
              }
            }
            OS.SetScrollInfo(handle, SB_VERT, info, true);
            int code = OS.SendMessage(handle, WM_VSCROLL, wParam, lParam);
            OS.GetScrollInfo(handle, SB_VERT, info);
            OS.SetScrollInfo(hwndParent, SB_VERT, info, true);
            return code;
          }
      }
      return callWindowProc(hwnd, msg, wParam, lParam);
    }
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
