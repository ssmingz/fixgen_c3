class PlaceHold {
  LRESULT WM_SYSKEYDOWN(int wParam, int lParam) {
    if (wParam != OS.VK_F10) {
      if ((lParam & 0x20000000) == 0) {
        return null;
      }
    }
    switch (wParam) {
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
    int mapKey = (OS.IsWinCE) ? 0 : OS.MapVirtualKey(wParam, 2);
    display.lastVirtual = (mapKey == 0) || (display.numpadKey(wParam) != 0);
    if (display.lastVirtual) {
      display.lastKey = wParam;
      if (display.lastKey == OS.VK_DELETE) {
        display.lastAscii = 0x7f;
      }
      if ((OS.VK_NUMPAD0 <= display.lastKey) && (display.lastKey <= OS.VK_DIVIDE)) {
        if (display.asciiKey(lastKey) != 0) {
          return null;
        }
        display.lastAscii = display.numpadKey(lastKey);
      }
    } else {
      display.lastKey = OS.CharLower(((short) (mapKey)));
      if (OS.IsWinNT) {
        return null;
      }
      if (wParam != OS.VK_RETURN) {
        return null;
      }
      display.lastAscii = '\r';
    }
    if (!sendKeyEvent(KeyDown, WM_SYSKEYDOWN, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
