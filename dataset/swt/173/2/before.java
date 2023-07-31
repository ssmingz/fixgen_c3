class PlaceHold {
  LRESULT WM_IME_CHAR(int wParam, int lParam) {
    Display display = this.display;
    display.lastKey = 0;
    display.lastAscii = wParam;
    display.lastVirtual = display.lastNull = display.lastDead = false;
    if (!sendKeyEvent(KeyDown, WM_IME_CHAR, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    ignoreCharacter = true;
    int result = callWindowProc(WM_IME_CHAR, wParam, lParam);
    MSG msg = new MSG();
    while (OS.PeekMessage(msg, handle, WM_CHAR, WM_CHAR, PM_REMOVE)) {
      OS.TranslateMessage(msg);
      OS.DispatchMessage(msg);
    }
    ignoreCharacter = false;
    sendKeyEvent(KeyUp, WM_IME_CHAR, wParam, lParam);
    display.lastKey = display.lastAscii = 0;
    return new LRESULT(result);
  }
}
