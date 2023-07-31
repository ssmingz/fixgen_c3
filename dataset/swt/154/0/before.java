class PlaceHold {
  LRESULT WM_ACTIVATE(int wParam, int lParam) {
    LRESULT result = super.WM_ACTIVATE(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((wParam & 0xffff) == 0) {
      Shell shell = getShell();
      shell.setActiveControl(null);
      if (isDisposed()) {
        return LRESULT.ZERO;
      }
      sendEvent(Deactivate);
      if (isDisposed()) {
        return LRESULT.ZERO;
      }
      saveFocus();
    } else {
      sendEvent(Activate);
      if (isDisposed()) {
        return LRESULT.ZERO;
      }
      if (restoreFocus()) {
        return LRESULT.ZERO;
      }
    }
    return result;
  }
}
