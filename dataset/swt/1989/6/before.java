class PlaceHold {
  LRESULT WM_NCLBUTTONDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_NCLBUTTONDOWN(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (!display.ignoreRestoreFocus) {
      return result;
    }
    Display display = this.display;
    int hwndActive = 0;
    boolean fixActive = OS.IsWin95 && (display.lastHittest == OS.HTCAPTION);
    if (fixActive) {
      hwndActive = OS.SetActiveWindow(handle);
    }
    display.lockActiveWindow = true;
    int code = callWindowProc(WM_NCLBUTTONDOWN, wParam, lParam);
    display.lockActiveWindow = false;
    if (fixActive) {
      OS.SetActiveWindow(hwndActive);
    }
    Control focusControl = display.lastHittestControl;
    if ((focusControl != null) && (!focusControl.isDisposed())) {
      focusControl.setFocus();
    }
    display.lastHittestControl = null;
    display.ignoreRestoreFocus = false;
    return new LRESULT(code);
  }
}
