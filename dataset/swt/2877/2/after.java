class PlaceHold {
  long callWindowProc(long hwnd, int msg, long wParam, long lParam, boolean forceSelect) {
    if (handle == 0) {
      return 0;
    }
    if (handle != hwnd) {
      return OS.CallWindowProc(HeaderProc, hwnd, msg, wParam, lParam);
    }
    int topIndex = 0;
    boolean checkSelection = false;
    boolean checkActivate = false;
    boolean redraw = false;
    switch (msg) {
      case OS.WM_KEYDOWN:
        checkActivate = true;
      case OS.WM_CHAR:
      case OS.WM_IME_CHAR:
      case OS.WM_KEYUP:
      case OS.WM_SYSCHAR:
      case OS.WM_SYSKEYDOWN:
      case OS.WM_SYSKEYUP:
      case OS.WM_HSCROLL:
      case OS.WM_VSCROLL:
      case OS.WM_WINDOWPOSCHANGED:
        redraw = ((findImageControl() != null) && getDrawing()) && OS.IsWindowVisible(handle);
        if (redraw) {
          OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
          OS.SendMessage(handle, LVM_SETBKCOLOR, 0, 0xffffff);
        }
      case OS.WM_LBUTTONDBLCLK:
      case OS.WM_LBUTTONDOWN:
      case OS.WM_LBUTTONUP:
      case OS.WM_MBUTTONDBLCLK:
      case OS.WM_MBUTTONDOWN:
      case OS.WM_MBUTTONUP:
      case OS.WM_MOUSEHOVER:
      case OS.WM_MOUSELEAVE:
      case OS.WM_MOUSEMOVE:
      case OS.WM_MOUSEWHEEL:
      case OS.WM_RBUTTONDBLCLK:
      case OS.WM_RBUTTONDOWN:
      case OS.WM_RBUTTONUP:
      case OS.WM_XBUTTONDBLCLK:
      case OS.WM_XBUTTONDOWN:
      case OS.WM_XBUTTONUP:
        checkSelection = true;
      case OS.WM_SETFONT:
      case OS.WM_TIMER:
        {
          if (findImageControl() != null) {
            topIndex = ((int) (OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0)));
          }
        }
    }
    boolean oldSelected = wasSelected;
    if (checkSelection) {
      wasSelected = false;
    }
    if (checkActivate) {
      ignoreActivate = true;
    }
    boolean fixPaint = false;
    if (msg == OS.WM_PAINT) {
      int bits0 = OS.GetWindowLong(handle, GWL_STYLE);
      if ((bits0 & OS.LVS_NOCOLUMNHEADER) == 0) {
        long hwndParent = OS.GetParent(handle);
        long hwndOwner = 0;
        while (hwndParent != 0) {
          int bits1 = OS.GetWindowLong(hwndParent, GWL_EXSTYLE);
          if ((bits1 & OS.WS_EX_COMPOSITED) != 0) {
            fixPaint = true;
            break;
          }
          hwndOwner = OS.GetWindow(hwndParent, GW_OWNER);
          if (hwndOwner != 0) {
            break;
          }
          hwndParent = OS.GetParent(hwndParent);
        }
      }
    }
    boolean fixScroll = false;
    if (((style & SWT.H_SCROLL) == 0) || ((style & SWT.V_SCROLL) == 0)) {
      switch (msg) {
        case OS.WM_PAINT:
        case OS.WM_NCPAINT:
        case OS.WM_WINDOWPOSCHANGING:
          {
            int bits = OS.GetWindowLong(hwnd, GWL_STYLE);
            if (((style & SWT.H_SCROLL) == 0) && ((bits & OS.WS_HSCROLL) != 0)) {
              fixScroll = true;
              bits &= ~OS.WS_HSCROLL;
            }
            if (((style & SWT.V_SCROLL) == 0) && ((bits & OS.WS_VSCROLL) != 0)) {
              fixScroll = true;
              bits &= ~OS.WS_VSCROLL;
            }
            if (fixScroll) {
              OS.SetWindowLong(handle, GWL_STYLE, bits);
            }
          }
      }
    }
    long code = 0;
    if (fixPaint) {
      PAINTSTRUCT ps = new PAINTSTRUCT();
      long hDC = OS.BeginPaint(hwnd, ps);
      code = OS.CallWindowProc(TableProc, hwnd, WM_PAINT, hDC, lParam);
      OS.EndPaint(hwnd, ps);
    } else {
      code = OS.CallWindowProc(TableProc, hwnd, msg, wParam, lParam);
    }
    if (fixScroll) {
      int flags = OS.RDW_FRAME | OS.RDW_INVALIDATE;
      OS.RedrawWindow(handle, null, 0, flags);
    }
    if (checkActivate) {
      ignoreActivate = false;
    }
    if (checkSelection) {
      if (wasSelected || forceSelect) {
        Event event = new Event();
        int index = ((int) (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED)));
        if (index != (-1)) {
          event.item = _getItem(index);
        }
        sendSelectionEvent(Selection, event, false);
      }
      wasSelected = oldSelected;
    }
    switch (msg) {
      case OS.WM_KEYDOWN:
      case OS.WM_CHAR:
      case OS.WM_IME_CHAR:
      case OS.WM_KEYUP:
      case OS.WM_SYSCHAR:
      case OS.WM_SYSKEYDOWN:
      case OS.WM_SYSKEYUP:
      case OS.WM_HSCROLL:
      case OS.WM_VSCROLL:
      case OS.WM_WINDOWPOSCHANGED:
        if (redraw) {
          if (OS.WIN32_VERSION < OS.VERSION(6, 3)) {
            OS.SendMessage(handle, LVM_SETBKCOLOR, 0, CLR_NONE);
          }
          OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
          OS.InvalidateRect(handle, null, true);
          long hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
          if (hwndHeader != 0) {
            OS.InvalidateRect(hwndHeader, null, true);
          }
        }
      case OS.WM_LBUTTONDBLCLK:
      case OS.WM_LBUTTONDOWN:
      case OS.WM_LBUTTONUP:
      case OS.WM_MBUTTONDBLCLK:
      case OS.WM_MBUTTONDOWN:
      case OS.WM_MBUTTONUP:
      case OS.WM_MOUSEHOVER:
      case OS.WM_MOUSELEAVE:
      case OS.WM_MOUSEMOVE:
      case OS.WM_MOUSEWHEEL:
      case OS.WM_RBUTTONDBLCLK:
      case OS.WM_RBUTTONDOWN:
      case OS.WM_RBUTTONUP:
      case OS.WM_XBUTTONDBLCLK:
      case OS.WM_XBUTTONDOWN:
      case OS.WM_XBUTTONUP:
      case OS.WM_SETFONT:
      case OS.WM_TIMER:
        {
          if (findImageControl() != null) {
            if (topIndex != OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0)) {
              OS.InvalidateRect(handle, null, true);
            }
          }
          break;
        }
      case OS.WM_PAINT:
        painted = true;
        break;
    }
    return code;
  }
}
