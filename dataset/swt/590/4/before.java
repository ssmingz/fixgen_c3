class PlaceHold {
  boolean sendKeyEvent(NSEvent nsEvent, int type) {
    boolean result = super.sendKeyEvent(nsEvent, type);
    if (!result) {
      return result;
    }
    if (type != SWT.KeyDown) {
      return result;
    }
    int stateMask = 0;
    int modifierFlags = nsEvent.modifierFlags();
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
    if (stateMask == SWT.COMMAND) {
      short keyCode = nsEvent.keyCode();
      switch (keyCode) {
        case 7:
          if ((style & SWT.PASSWORD) == 0) {
            cut();
          }
          return false;
        case 8:
          if ((style & SWT.PASSWORD) == 0) {
            copy();
          }
          return false;
        case 9:
          paste();
          return false;
      }
    }
    if ((style & SWT.SINGLE) != 0) {
      short keyCode = nsEvent.keyCode();
      switch (keyCode) {
        case 76:
        case 36:
          postEvent(DefaultSelection);
      }
    }
    return result;
  }
}
