class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if (hwnd != handle) {
      int hwndText = OS.GetDlgItem(handle, CBID_EDIT);
      int hwndList = OS.GetDlgItem(handle, CBID_LIST);
      if (((hwndText != 0) && (hwnd == hwndText)) || ((hwndList != 0) && (hwnd == hwndList))) {
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
          case OS.WM_SETTEXT:
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
    }
    if (msg == OS.CB_SETCURSEL) {
      if ((style & SWT.READ_ONLY) != 0) {
        if (hooks(Verify) || filters(Verify)) {
          String oldText = getText();
          String newText = null;
          if (wParam == (-1)) {
            newText = "";
          } else if ((0 <= wParam) && (wParam < getItemCount())) {
            newText = getItem(wParam);
          }
          if ((newText != null) && (!newText.equals(oldText))) {
            int length = OS.GetWindowTextLength(handle);
            oldText = newText;
            newText = verifyText(newText, 0, length, null);
            if (newText == null) {
              return 0;
            }
            if (!newText.equals(oldText)) {
              int index = indexOf(newText);
              if ((index != (-1)) && (index != wParam)) {
                return callWindowProc(handle, CB_SETCURSEL, index, lParam);
              }
            }
          }
        }
      }
    }
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
