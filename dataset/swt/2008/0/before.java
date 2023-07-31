class PlaceHold {
  int kEventWindowDeactivated(int nextHandler, int theEvent, int userData) {
    int result = super.kEventWindowDeactivated(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    sendEvent(Deactivate);
    saveFocus();
    if (savedFocus != null) {
      display.ignoreFocus = true;
      OS.ClearKeyboardFocus(shellHandle);
      display.ignoreFocus = false;
      if (!savedFocus.isDisposed()) {
        savedFocus.sendFocusEvent(false);
      }
    }
    Display display = getDisplay();
    display.updateMenuBar(null);
    return result;
  }
}
