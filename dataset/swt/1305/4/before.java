class PlaceHold {
  LRESULT WM_IME_CHAR(int wParam, int lParam) {
    Display display = this.display;
    display.lastKey = 0;
    display.lastAscii = wParam;
    display.lastVirtual = display.lastNull = false;
    if (!sendKeyEvent(KeyDown, WM_IME_CHAR, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    sendKeyEvent(KeyUp, WM_IME_CHAR, wParam, lParam);
    display.lastKey = display.lastAscii = 0;
    return LRESULT.ZERO;
  }
}
