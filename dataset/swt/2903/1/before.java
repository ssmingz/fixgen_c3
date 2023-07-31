class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if (hwnd != handle) {
      switch (msg) {
        case OS.WM_CAPTURECHANGED:
          {
            if (OS.COMCTL32_MAJOR < 6) {
              if (lParam != 0) {
                int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
                if (lParam != hwndHeader) {
                  OS.InvalidateRect(hwndHeader, null, true);
                }
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
              int hitTest = ((short) (lParam & 0xffff));
              if (hitTest == OS.HTCLIENT) {
                HDHITTESTINFO pinfo = new HDHITTESTINFO();
                int pos = OS.GetMessagePos();
                POINT pt = new POINT();
                pt.x = ((short) (pos & 0xffff));
                pt.y = ((short) (pos >> 16));
                OS.ScreenToClient(hwnd, pt);
                pinfo.x = pt.x;
                pinfo.y = pt.y;
                int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
                int index = OS.SendMessage(hwndHeader, HDM_HITTEST, 0, pinfo);
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
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
