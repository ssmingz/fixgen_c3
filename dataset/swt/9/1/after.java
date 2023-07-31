class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int result = super.kEventMouseDown(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    Shell shell = getShell();
    shell.bringToTop(true);
    if (isDisposed()) {
      return OS.noErr;
    }
    Control oldFocus = display.getFocusControl();
    display.ignoreFocus = true;
    result = OS.CallNextEventHandler(nextHandler, theEvent);
    display.ignoreFocus = false;
    if (oldFocus != this) {
      if ((oldFocus != null) && (!oldFocus.isDisposed())) {
        oldFocus.sendFocusEvent(FocusOut, false);
      }
      if ((!isDisposed()) && isEnabled()) {
        sendFocusEvent(FocusIn, false);
      }
    }
    return result;
  }
}
