class PlaceHold {
  LRESULT WM_ACTIVATE(int wParam, int lParam) {
    LRESULT result = super.WM_ACTIVATE(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.GetParent(lParam) == handle) {
      TCHAR buffer = new TCHAR(0, 128);
      OS.GetClassName(lParam, buffer, buffer.length());
      String className = buffer.toString(0, buffer.strlen());
      if (className.equals(AWT_WINDOW_CLASS)) {
        return LRESULT.ONE;
      }
    }
    if ((wParam & 0xffff) != 0) {
      if ((wParam >> 16) != 0) {
        return result;
      }
      Control control = display.findControl(lParam);
      if ((control == null) || (control instanceof Shell)) {
        if (this instanceof Shell) {
          sendEvent(Activate);
          if (isDisposed()) {
            return LRESULT.ZERO;
          }
        }
      }
      if (restoreFocus()) {
        return LRESULT.ZERO;
      }
    } else {
      Control control = display.findControl(lParam);
      if ((control == null) || (control instanceof Shell)) {
        if (this instanceof Shell) {
          sendEvent(Deactivate);
          if (isDisposed()) {
            return LRESULT.ZERO;
          }
          Shell shell = getShell();
          shell.setActiveControl(null);
          if (isDisposed()) {
            return LRESULT.ZERO;
          }
        }
      }
      saveFocus();
    }
    return result;
  }
}
