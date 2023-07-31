class PlaceHold {
  LRESULT WM_SETFOCUS(int wParam, int lParam) {
    int code = callWindowProc(WM_SETFOCUS, wParam, lParam);
    Shell shell = getShell();
    sendEvent(FocusIn);
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
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
