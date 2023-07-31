class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    if ((hwnd == hwndText) || (hwnd == hwndUpDown)) {
      LRESULT result = null;
      switch (msg) {
        case OS.WM_CHAR:
          result = wmChar(hwnd, wParam, lParam);
          break;
        case OS.WM_IME_CHAR:
          result = wmIMEChar(hwnd, wParam, lParam);
          break;
        case OS.WM_KEYDOWN:
          result = wmKeyDown(hwnd, wParam, lParam);
          break;
        case OS.WM_KEYUP:
          result = wmKeyUp(hwnd, wParam, lParam);
          break;
        case OS.WM_SYSCHAR:
          result = wmSysChar(hwnd, wParam, lParam);
          break;
        case OS.WM_SYSKEYDOWN:
          result = wmSysKeyDown(hwnd, wParam, lParam);
          break;
        case OS.WM_SYSKEYUP:
          result = wmSysKeyUp(hwnd, wParam, lParam);
          break;
        case OS.WM_CAPTURECHANGED:
          result = wmCaptureChanged(hwnd, wParam, lParam);
          break;
        case OS.WM_LBUTTONDBLCLK:
          result = wmLButtonDblClk(hwnd, wParam, lParam);
          break;
        case OS.WM_LBUTTONDOWN:
          result = wmLButtonDown(hwnd, wParam, lParam);
          break;
        case OS.WM_LBUTTONUP:
          result = wmLButtonUp(hwnd, wParam, lParam);
          break;
        case OS.WM_MBUTTONDBLCLK:
          result = wmMButtonDblClk(hwnd, wParam, lParam);
          break;
        case OS.WM_MBUTTONDOWN:
          result = wmMButtonDown(hwnd, wParam, lParam);
          break;
        case OS.WM_MBUTTONUP:
          result = wmMButtonUp(hwnd, wParam, lParam);
          break;
        case OS.WM_MOUSEHOVER:
          result = wmMouseHover(hwnd, wParam, lParam);
          break;
        case OS.WM_MOUSELEAVE:
          result = wmMouseLeave(hwnd, wParam, lParam);
          break;
        case OS.WM_MOUSEMOVE:
          result = wmMouseMove(hwnd, wParam, lParam);
          break;
        case OS.WM_RBUTTONDBLCLK:
          result = wmRButtonDblClk(hwnd, wParam, lParam);
          break;
        case OS.WM_RBUTTONDOWN:
          result = wmRButtonDown(hwnd, wParam, lParam);
          break;
        case OS.WM_RBUTTONUP:
          result = wmRButtonUp(hwnd, wParam, lParam);
          break;
        case OS.WM_XBUTTONDBLCLK:
          result = wmXButtonDblClk(hwnd, wParam, lParam);
          break;
        case OS.WM_XBUTTONDOWN:
          result = wmXButtonDown(hwnd, wParam, lParam);
          break;
        case OS.WM_XBUTTONUP:
          result = wmXButtonUp(hwnd, wParam, lParam);
          break;
        case OS.WM_SETFOCUS:
          result = wmSetFocus(hwnd, wParam, lParam);
          break;
        case OS.WM_KILLFOCUS:
          result = wmKillFocus(hwnd, wParam, lParam);
          break;
        case OS.WM_PAINT:
          result = wmPaint(hwnd, wParam, lParam);
          break;
        case OS.WM_CONTEXTMENU:
          result = wmContextMenu(hwnd, wParam, lParam);
          break;
        case OS.WM_CLEAR:
        case OS.WM_CUT:
        case OS.WM_PASTE:
        case OS.WM_UNDO:
        case OS.EM_UNDO:
          if (hwnd == hwndText) {
            result = wmClipboard(hwnd, msg, wParam, lParam);
          }
          break;
      }
      if (result != null) {
        return result.value;
      }
      return callWindowProc(hwnd, msg, wParam, lParam);
    }
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
