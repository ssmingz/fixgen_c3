class PlaceHold {
  boolean sendKeyEvent(NSEvent nsEvent, int type) {
    boolean result = super.sendKeyEvent(nsEvent, type);
    if (!result) {
      return result;
    }
    if (type != SWT.KeyDown) {
      return result;
    }
    if ((style & SWT.CALENDAR) == 0) {
      short keyCode = nsEvent.keyCode();
      switch (keyCode) {
        case 76:
        case 36:
          sendSelectionEvent(DefaultSelection);
      }
    }
    return result;
  }
}
