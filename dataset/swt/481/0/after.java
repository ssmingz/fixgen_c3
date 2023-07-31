class PlaceHold {
  LRESULT WM_KILLFOCUS(int wParam, int lParam) {
    int code = callWindowProc(WM_KILLFOCUS, wParam, lParam);
    Display display = getDisplay();
    Shell shell = getShell();
    sendEvent(FocusOut);
    if (!shell.isDisposed()) {
      Control control = display.findControl(wParam);
      if ((control == null) || (shell != control.getShell())) {
        shell.setActiveControl(null);
      }
    }
    if (isDisposed()) {
      return LRESULT.ZERO;
    }
    if (code == 0) {
      return LRESULT.ZERO;
    }
    return new LRESULT(code);
  }
}
