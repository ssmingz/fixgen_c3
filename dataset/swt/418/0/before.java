class PlaceHold {
  LRESULT WM_KEYUP(int wParam, int lParam) {
    Display display = getDisplay();
    if (OS.IsWinCE) {
      if ((OS.VK_APP1 <= wParam) && (wParam <= OS.VK_APP6)) {
        display.lastVirtual = false;
        display.lastKey = display.lastAscii = 0;
        Event event = new Event();
        event.detail = (wParam - OS.VK_APP1) + 1;
        int type = ((lParam & 0x40000000) != 0) ? SWT.HardKeyUp : SWT.HardKeyDown;
        if (setInputState(event, type)) {
          sendEvent(type, event);
        }
        return null;
      }
    }
    if (!hooks(KeyUp)) {
      display.lastVirtual = false;
      display.lastKey = display.lastAscii = 0;
      return null;
    }
    int mapKey = OS.MapVirtualKey(wParam, 2);
    if (OS.IsWinNT) {
      if ((mapKey & 0x80000000) != 0) {
        return null;
      }
    } else if ((mapKey & 0x8000) != 0) {
      return null;
    }
    display.lastVirtual = mapKey == 0;
    if (display.lastVirtual) {
      display.lastKey = wParam;
    } else {
      if (display.lastKey == 0) {
        display.lastAscii = 0;
        return null;
      }
      display.lastVirtual = display.isVirtualKey(display.lastKey);
    }
    LRESULT result = null;
    if (!sendKeyEvent(KeyUp, WM_KEYUP, wParam, lParam)) {
      result = LRESULT.ZERO;
    }
    display.lastVirtual = false;
    display.lastKey = display.lastAscii = 0;
    return result;
  }
}
