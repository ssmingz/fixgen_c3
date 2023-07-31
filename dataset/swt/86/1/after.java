class PlaceHold {
  int processFocusIn(int int0, int int1, int int2) {
    Shell shell = _getShell();
    sendEvent(FocusIn);
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    return 0;
  }
}
