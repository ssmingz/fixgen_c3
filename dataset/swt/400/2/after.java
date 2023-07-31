class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_KEYDOWN(wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (((int) (wParam))) {
      case OS.VK_LEFT:
      case OS.VK_RIGHT:
      case OS.VK_UP:
      case OS.VK_DOWN:
        if (OS.GetKeyState(VK_LBUTTON) < 0) {
          return result;
        }
        int step = (OS.GetKeyState(VK_CONTROL) < 0) ? INCREMENT : PAGE_INCREMENT;
        POINT pt = new POINT();
        if ((style & SWT.VERTICAL) != 0) {
          if ((wParam == OS.VK_UP) || (wParam == OS.VK_DOWN)) {
            break;
          }
          pt.x = (wParam == OS.VK_LEFT) ? -step : step;
        } else {
          if ((wParam == OS.VK_LEFT) || (wParam == OS.VK_RIGHT)) {
            break;
          }
          pt.y = (wParam == OS.VK_UP) ? -step : step;
        }
        int hwndTrack = parent.handle;
        OS.MapWindowPoints(handle, hwndTrack, pt, 1);
        RECT rect = new RECT();
        RECT clientRect = new RECT();
        OS.GetWindowRect(handle, rect);
        int width = rect.right - rect.left;
        int height = rect.bottom - rect.top;
        OS.GetClientRect(hwndTrack, clientRect);
        int clientWidth = clientRect.right - clientRect.left;
        int clientHeight = clientRect.bottom - clientRect.top;
        int newX = lastX;
        int newY = lastY;
        if ((style & SWT.VERTICAL) != 0) {
          newX = Math.min(Math.max(0, pt.x - startX), clientWidth - width);
        } else {
          newY = Math.min(Math.max(0, pt.y - startY), clientHeight - height);
        }
        if ((newX == lastX) && (newY == lastY)) {
          return result;
        }
        POINT cursorPt = new POINT();
        cursorPt.x = pt.x;
        cursorPt.y = pt.y;
        OS.ClientToScreen(hwndTrack, cursorPt);
        if ((style & SWT.VERTICAL) != 0) {
          cursorPt.y += height / 2;
        } else {
          cursorPt.x += width / 2;
        }
        OS.SetCursorPos(cursorPt.x, cursorPt.y);
        Event event = new Event();
        event.x = newX;
        event.y = newY;
        event.width = width;
        event.height = height;
        sendSelectionEvent(Selection, event, true);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        if (event.doit) {
          if ((style & SWT.SMOOTH) != 0) {
            setBounds(event.x, event.y, width, height);
          }
        }
        return result;
    }
    return result;
  }
}
