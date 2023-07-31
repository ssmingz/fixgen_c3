class PlaceHold {
  LRESULT WM_KEYUP(int wParam, int lParam) {
    Display display = this.display;
    if (OS.IsWinCE) {
      if ((OS.VK_APP1 <= wParam) && (wParam <= OS.VK_APP6)) {
        display.lastKey = display.lastAscii = 0;
        display.lastVirtual = display.lastNull = display.lastDead = false;
        Event event = new Event();
        event.detail = (wParam - OS.VK_APP1) + 1;
        int type = ((lParam & 0x40000000) != 0) ? SWT.HardKeyUp : SWT.HardKeyDown;
        if (setInputState(event, type)) {
          sendEvent(type, event);
        }
        return null;
      }
    }
    if ((!hooks(KeyUp)) && (!display.filters(KeyUp))) {
      display.lastKey = display.lastAscii = 0;
      display.lastVirtual = display.lastNull = display.lastDead = false;
      return null;
    }
    int mapKey = (OS.IsWinCE) ? 0 : OS.MapVirtualKey(wParam, 2);
    if (OS.IsWinNT) {
      if ((mapKey & 0x80000000) != 0) {
        return null;
      }
    } else if ((mapKey & 0x8000) != 0) {
      return null;
    }
    if (display.lastDead) {
      return null;
    }
    display.lastVirtual = (mapKey == 0) || (display.numpadKey(wParam) != 0);
    if (display.lastVirtual) {
      display.lastKey = wParam;
    } else {
      if (wParam == OS.VK_CANCEL) {
        display.lastVirtual = true;
      }
      if (display.lastKey == 0) {
        display.lastAscii = 0;
        display.lastNull = display.lastDead = false;
        return null;
      }
    }
    LRESULT result = null;
    if (!sendKeyEvent(KeyUp, WM_KEYUP, wParam, lParam)) {
      result = LRESULT.ZERO;
    }
    display.lastKey = display.lastAscii = 0;
    display.lastVirtual = display.lastNull = display.lastDead = false;
    return result;
  }
}
