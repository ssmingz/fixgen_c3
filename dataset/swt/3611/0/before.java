class PlaceHold {
  int messageProc(int hwnd, int msg, int wParam, int lParam) {
    switch (lParam) {
      case OS.WM_LBUTTONDOWN:
        if (hooks(Selection)) {
          OS.SetForegroundWindow(hwnd);
          postEvent(Selection);
        }
        break;
      case OS.WM_LBUTTONDBLCLK:
      case OS.WM_RBUTTONDBLCLK:
        if (hooks(DefaultSelection)) {
          OS.SetForegroundWindow(hwnd);
          postEvent(DefaultSelection);
        }
        break;
      case OS.WM_RBUTTONUP:
        {
          if (hooks(MenuDetect)) {
            OS.SetForegroundWindow(hwnd);
            sendEvent(MenuDetect);
            if (isDisposed()) {
              return 0;
            }
          }
          break;
        }
      case OS.NIN_BALLOONSHOW:
        if ((toolTip != null) && (!toolTip.visible)) {
          toolTip.visible = true;
          if (toolTip.hooks(Show)) {
            OS.SetForegroundWindow(hwnd);
            toolTip.sendEvent(Show);
            if (isDisposed()) {
              return 0;
            }
          }
        }
        break;
      case OS.NIN_BALLOONHIDE:
      case OS.NIN_BALLOONTIMEOUT:
      case OS.NIN_BALLOONUSERCLICK:
        if (toolTip != null) {
          if (toolTip.visible) {
            toolTip.visible = false;
            if (toolTip.hooks(Hide)) {
              OS.SetForegroundWindow(hwnd);
              toolTip.sendEvent(Hide);
              if (isDisposed()) {
                return 0;
              }
            }
          }
          if (lParam == OS.NIN_BALLOONUSERCLICK) {
            if (toolTip.hooks(Selection)) {
              OS.SetForegroundWindow(hwnd);
              toolTip.postEvent(Selection);
              if (isDisposed()) {
                return 0;
              }
            }
          }
        }
        break;
    }
    display.wakeThread();
    return 0;
  }
}
