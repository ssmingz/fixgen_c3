class PlaceHold {
  LRESULT WM_SETFOCUS(int wParam, int lParam) {
    Shell shell = getShell();
    sendEvent(FocusIn);
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    if (isDisposed()) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
