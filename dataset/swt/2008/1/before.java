class PlaceHold {
  int kEventWindowActivated(int nextHandler, int theEvent, int userData) {
    int result = super.kEventWindowActivated(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int[] outScope = new int[1];
    OS.GetWindowActivationScope(shellHandle, outScope);
    if (outScope[0] == OS.kWindowActivationScopeNone) {
      return result;
    }
    Display display = getDisplay();
    display.updateMenuBar(this);
    sendEvent(Activate);
    restoreFocus();
    return result;
  }
}
