class PlaceHold {
  LRESULT wmSysKeyDown(int hwnd, int wParam, int lParam) {
    if (wParam != OS.VK_F10) {
      if ((lParam & 0x20000000) == 0) {
        return null;
      }
    }
    switch (((int) (wParam))) {
      case OS.VK_F4:
        {
          int hwndShell = hwnd;
          while (OS.GetParent(hwndShell) != 0) {
            if (OS.GetWindow(hwndShell, GW_OWNER) != 0) {
              break;
            }
            hwndShell = OS.GetParent(hwndShell);
          }
          int bits = OS.GetWindowLong(hwndShell, GWL_STYLE);
          if ((bits & OS.WS_SYSMENU) != 0) {
            return null;
          }
        }
    }
    switch (((int) (wParam))) {
      case OS.VK_SHIFT:
      case OS.VK_MENU:
      case OS.VK_CONTROL:
      case OS.VK_CAPITAL:
      case OS.VK_NUMLOCK:
      case OS.VK_SCROLL:
        if ((lParam & 0x40000000) != 0) {
          return null;
        }
    }
    display.lastAscii = display.lastKey = 0;
    display.lastVirtual = display.lastNull = display.lastDead = false;
    int mapKey = 0;
    if (OS.IsWinCE) {
      switch (((int) (wParam))) {
        case OS.VK_BACK:
          mapKey = SWT.BS;
          break;
        case OS.VK_RETURN:
          mapKey = SWT.CR;
          break;
        case OS.VK_DELETE:
          mapKey = SWT.DEL;
          break;
        case OS.VK_ESCAPE:
          mapKey = SWT.ESC;
          break;
        case OS.VK_TAB:
          mapKey = SWT.TAB;
          break;
      }
    } else {
      mapKey = OS.MapVirtualKey(((int) (wParam)), 2);
    }
    display.lastVirtual = (mapKey == 0) || (display.numpadKey(((int) (wParam))) != 0);
    if (display.lastVirtual) {
      display.lastKey = ((int) (wParam));
      if (display.lastKey == OS.VK_DELETE) {
        display.lastAscii = 0x7f;
      }
      if ((OS.VK_NUMPAD0 <= display.lastKey) && (display.lastKey <= OS.VK_DIVIDE)) {
        switch (display.lastKey) {
          case OS.VK_MULTIPLY:
          case OS.VK_ADD:
          case OS.VK_SUBTRACT:
          case OS.VK_DECIMAL:
          case OS.VK_DIVIDE:
            return null;
        }
        display.lastAscii = display.numpadKey(display.lastKey);
      }
    } else {
      display.lastKey = ((int) (OS.CharLower(((short) (mapKey)))));
      if (OS.IsWinNT) {
        return null;
      }
      if (wParam != OS.VK_RETURN) {
        return null;
      }
      display.lastAscii = '\r';
    }
    if (!sendKeyEvent(KeyDown, WM_SYSKEYDOWN, wParam, lParam)) {
      return LRESULT.ONE;
    }
    return null;
  }
}
