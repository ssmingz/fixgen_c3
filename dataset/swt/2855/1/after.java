class PlaceHold {
  LRESULT wmSysChar(int hwnd, int wParam, int lParam) {
    Display display = this.display;
    display.lastAscii = wParam;
    display.lastNull = wParam == 0;
    if ((!hooks(KeyDown)) && (!display.filters(KeyDown))) {
      return null;
    }
    boolean oldKeyHit = display.mnemonicKeyHit;
    display.mnemonicKeyHit = true;
    int result = callWindowProc(hwnd, WM_SYSCHAR, wParam, lParam);
    boolean consumed = false;
    if (!display.mnemonicKeyHit) {
      consumed = !sendKeyEvent(KeyDown, WM_SYSCHAR, wParam, lParam);
    }
    consumed |= display.mnemonicKeyHit;
    display.mnemonicKeyHit = oldKeyHit;
    return consumed ? LRESULT.ONE : new LRESULT(result);
  }
}
