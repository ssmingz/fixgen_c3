class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    boolean redraw = false;
    switch (msg) {
      case OS.WM_ERASEBKGND:
        {
          if (findImageControl() != null) {
            return 0;
          }
          break;
        }
      case OS.WM_HSCROLL:
      case OS.WM_VSCROLL:
        {
          redraw = ((findImageControl() != null) && (drawCount == 0)) && OS.IsWindowVisible(handle);
          if (redraw) {
            OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
          }
          break;
        }
      case OS.WM_PAINT:
        {
          boolean doubleBuffer = findImageControl() != null;
          boolean drawMessage = false;
          if (((style & SWT.SINGLE) != 0) && (message.length() > 0)) {
            if ((!OS.IsWinCE) && (OS.WIN32_VERSION < OS.VERSION(6, 0))) {
              drawMessage = (hwnd != OS.GetFocus()) && (OS.GetWindowTextLength(handle) == 0);
            }
          }
          if (doubleBuffer || drawMessage) {
            int paintDC = 0;
            PAINTSTRUCT ps = new PAINTSTRUCT();
            paintDC = OS.BeginPaint(handle, ps);
            int width = ps.right - ps.left;
            int height = ps.bottom - ps.top;
            if ((width != 0) && (height != 0)) {
              int hDC = paintDC;
              int hBitmap = 0;
              int hOldBitmap = 0;
              POINT lpPoint1 = null;
              POINT lpPoint2 = null;
              if (doubleBuffer) {
                hDC = OS.CreateCompatibleDC(paintDC);
                lpPoint1 = new POINT();
                lpPoint2 = new POINT();
                OS.SetWindowOrgEx(hDC, ps.left, ps.top, lpPoint1);
                OS.SetBrushOrgEx(hDC, ps.left, ps.top, lpPoint2);
                hBitmap = OS.CreateCompatibleBitmap(paintDC, width, height);
                hOldBitmap = OS.SelectObject(hDC, hBitmap);
                RECT rect = new RECT();
                OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                drawBackground(hDC, rect);
              }
              OS.CallWindowProc(EditProc, hwnd, WM_PAINT, hDC, lParam);
              if (drawMessage) {
                RECT rect = new RECT();
                OS.GetClientRect(handle, rect);
                int margins = OS.SendMessage(handle, EM_GETMARGINS, 0, 0);
                rect.left += OS.LOWORD(margins);
                rect.right -= OS.HIWORD(margins);
                if ((style & SWT.BORDER) != 0) {
                  rect.left++;
                  rect.top++;
                  rect.right--;
                  rect.bottom--;
                }
                TCHAR buffer = new TCHAR(getCodePage(), message, false);
                int uFormat = OS.DT_EDITCONTROL;
                boolean rtl = (style & SWT.RIGHT_TO_LEFT) != 0;
                if (rtl) {
                  uFormat |= OS.DT_RTLREADING;
                }
                int alignment = style & ((SWT.LEFT | SWT.CENTER) | SWT.RIGHT);
                switch (alignment) {
                  case SWT.LEFT:
                    uFormat |= (rtl) ? OS.DT_RIGHT : OS.DT_LEFT;
                    break;
                  case SWT.CENTER:
                    uFormat |= OS.DT_CENTER;
                  case SWT.RIGHT:
                    uFormat |= (rtl) ? OS.DT_LEFT : OS.DT_RIGHT;
                    break;
                }
                int hFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
                int hOldFont = OS.SelectObject(hDC, hFont);
                OS.SetTextColor(hDC, OS.GetSysColor(COLOR_GRAYTEXT));
                OS.SetBkMode(hDC, TRANSPARENT);
                OS.DrawText(hDC, buffer, buffer.length(), rect, uFormat);
                OS.SelectObject(hDC, hOldFont);
              }
              if (doubleBuffer) {
                OS.SetWindowOrgEx(hDC, lpPoint1.x, lpPoint1.y, null);
                OS.SetBrushOrgEx(hDC, lpPoint2.x, lpPoint2.y, null);
                OS.BitBlt(paintDC, ps.left, ps.top, width, height, hDC, 0, 0, SRCCOPY);
                OS.SelectObject(hDC, hOldBitmap);
                OS.DeleteObject(hBitmap);
                OS.DeleteObject(hDC);
              }
            }
            OS.EndPaint(handle, ps);
            return 0;
          }
          break;
        }
    }
    int code = OS.CallWindowProc(EditProc, hwnd, msg, wParam, lParam);
    switch (msg) {
      case OS.WM_HSCROLL:
      case OS.WM_VSCROLL:
        {
          if (redraw) {
            OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
            OS.InvalidateRect(handle, null, true);
          }
          break;
        }
    }
    return code;
  }
}
