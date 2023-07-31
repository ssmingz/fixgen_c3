class PlaceHold {
  boolean sendKeyEvent(NSEvent nsEvent, int type) {
    boolean result = super.sendKeyEvent(nsEvent, type);
    if (!result) {
      return result;
    }
    int stateMask = 0;
    long modifierFlags = nsEvent.modifierFlags();
    if ((modifierFlags & OS.NSAlternateKeyMask) != 0) {
      stateMask |= SWT.ALT;
    }
    if ((modifierFlags & OS.NSShiftKeyMask) != 0) {
      stateMask |= SWT.SHIFT;
    }
    if ((modifierFlags & OS.NSControlKeyMask) != 0) {
      stateMask |= SWT.CONTROL;
    }
    if ((modifierFlags & OS.NSCommandKeyMask) != 0) {
      stateMask |= SWT.COMMAND;
    }
    if (type != SWT.KeyDown) {
      return result;
    }
    short keyCode = nsEvent.keyCode();
    if (stateMask == SWT.COMMAND) {
      switch (keyCode) {
        case 7:
          cut();
          return false;
        case 8:
          copy();
          return false;
        case 9:
          paste();
          return false;
        case 0:
          if ((style & SWT.READ_ONLY) == 0) {
            ((NSComboBox) (view)).selectText(null);
            return false;
          }
      }
    }
    switch (keyCode) {
      case 76:
      case 36:
        sendSelectionEvent(DefaultSelection);
    }
    return result;
  }
}
