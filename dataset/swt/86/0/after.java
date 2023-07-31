class PlaceHold {
  int processFocusOut(int int0, int int1, int int2) {
    Shell shell = _getShell();
    sendEvent(FocusOut);
    if (!shell.isDisposed()) {
      Display display = shell.getDisplay();
      Control control = display.getFocusControl();
      if ((control == null) || (shell != control.getShell())) {
        shell.setActiveControl(null);
      }
    }
    return 0;
  }
}
