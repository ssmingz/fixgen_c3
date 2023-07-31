class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
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
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      byte lead = ((byte) (wParam & 0xff));
      if (OS.IsDBCSLeadByte(lead)) {
        return null;
      }
    }
    int mapKey = (OS.IsWinCE) ? 0 : OS.MapVirtualKey(wParam, 2);
    if (OS.IsWinNT) {
      if ((mapKey & 0x80000000) != 0) {
        return null;
      }
    } else if ((mapKey & 0x8000) != 0) {
      return null;
    }
    MSG msg = new MSG();
    if (OS.PeekMessage(msg, handle, WM_DEADCHAR, WM_DEADCHAR, PM_NOREMOVE)) {
      display.lastDead = true;
      display.lastVirtual = mapKey == 0;
      display.lastKey = (display.lastVirtual) ? wParam : mapKey;
      return null;
    }
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
      if (wParam == OS.VK_CANCEL) {
        display.lastVirtual = true;
      }
      int asciiKey = display.asciiKey(wParam);
      if (asciiKey != 0) {
        if (asciiKey == ' ') {
          return null;
        }
        if (asciiKey != wParam) {
          return null;
        }
        if (wParam == OS.VK_CANCEL) {
          return null;
        }
      }
      if (OS.GetKeyState(VK_CONTROL) >= 0) {
        return null;
      }
      if (OS.GetKeyState(VK_SHIFT) < 0) {
        display.lastAscii = display.shiftedKey(wParam);
        if (display.lastAscii == 0) {
          display.lastAscii = mapKey;
        }
      } else {
        display.lastAscii = OS.CharLower(((short) (mapKey)));
      }
      if (display.lastAscii == '@') {
        return null;
      }
      display.lastAscii = display.controlKey(lastAscii);
    }
    if (!sendKeyEvent(KeyDown, WM_KEYDOWN, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
