class PlaceHold {
  boolean translateMnemonic(MSG msg) {
    if (msg.wParam < 0x20) {
      return false;
    }
    int hwnd = msg.hwnd;
    if (OS.GetKeyState(VK_MENU) >= 0) {
      int code = OS.SendMessage(hwnd, WM_GETDLGCODE, 0, 0);
      if ((code & OS.DLGC_WANTALLKEYS) != 0) {
        return false;
      }
      if ((code & OS.DLGC_BUTTON) == 0) {
        return false;
      }
    }
    Decorations shell = menuShell();
    if (shell.isVisible() && shell.isEnabled()) {
      display.lastAscii = msg.wParam;
      display.lastNull = display.lastDead = false;
      Event event = new Event();
      event.detail = SWT.TRAVERSE_MNEMONIC;
      if (setKeyState(event, Traverse, msg.wParam, msg.lParam)) {
        return translateMnemonic(event, null) || shell.translateMnemonic(event, this);
      }
    }
    return false;
  }
}
