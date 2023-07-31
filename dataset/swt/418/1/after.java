class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
    Display display = getDisplay();
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      byte lead = ((byte) (wParam & 0xff));
      if (OS.IsDBCSLeadByte(lead)) {
        display.lastAscii = display.lastKey = 0;
        display.lastVirtual = false;
        return null;
      }
    }
    if ((((((wParam == OS.VK_SHIFT) || (wParam == OS.VK_MENU)) || (wParam == OS.VK_CONTROL))
                || (wParam == OS.VK_CAPITAL))
            || (wParam == OS.VK_NUMLOCK))
        || (wParam == OS.VK_SCROLL)) {
      if ((lParam & 0x40000000) != 0) {
        return null;
      }
    }
    display.lastAscii = 0;
    display.lastKey = wParam;
    int mapKey = OS.MapVirtualKey(display.lastKey, 2);
    if (OS.IsWinNT) {
      if ((mapKey & 0x80000000) != 0) {
        return null;
      }
    } else if ((mapKey & 0x8000) != 0) {
      return null;
    }
    for (int i = 0; i < Control.ACCENTS.length; i++) {
      int value = OS.VkKeyScan(ACCENTS[i]);
      if (((value & 0xff) == wParam) && ((value & 0x600) == 0x600)) {
        return null;
      }
    }
    display.lastVirtual = mapKey == 0;
    if (display.lastVirtual) {
      if (display.lastKey == OS.VK_DELETE) {
        display.lastAscii = 127;
      }
      if ((OS.VK_NUMPAD0 <= display.lastKey) && (display.lastKey <= OS.VK_DIVIDE)) {
        if (display.asciiKey(display.lastKey) != 0) {
          return null;
        }
      }
    } else {
      if (OS.GetKeyState(VK_SHIFT) < 0) {
        display.lastKey = display.shiftedKey(display.lastKey);
        if (display.lastKey == 0) {
          display.lastKey = wParam;
        }
      } else {
        display.lastKey = OS.CharLower(((short) (mapKey)));
      }
      int newKey = display.asciiKey(wParam);
      if (newKey != 0) {
        if (newKey == OS.VK_SPACE) {
          display.lastVirtual = true;
          return null;
        }
        if (newKey != wParam) {
          return null;
        }
      }
      if (OS.GetKeyState(VK_CONTROL) >= 0) {
        display.lastKey = 0;
        return null;
      }
      display.lastVirtual = display.isVirtualKey(display.lastKey);
      display.lastAscii = display.controlKey(display.lastKey);
    }
    if (!sendKeyEvent(KeyDown, WM_KEYDOWN, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
