class PlaceHold {
  void handleKeyDown(Event event) {
    if (isDisposed()) {
      SWT.error(ERROR_WIDGET_DISPOSED);
    }
    if (clipboardSelection == null) {
      clipboardSelection = new Point(selection.x, selection.y);
    }
    newOrientation = SWT.NONE;
    event.stateMask &= SWT.MODIFIER_MASK;
    Event verifyEvent = new Event();
    verifyEvent.character = event.character;
    verifyEvent.keyCode = event.keyCode;
    verifyEvent.keyLocation = event.keyLocation;
    verifyEvent.stateMask = event.stateMask;
    verifyEvent.doit = true;
    notifyListeners(VerifyKey, verifyEvent);
    if (verifyEvent.doit) {
      if ((((event.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL) && (event.keyCode == SWT.SHIFT))
          && isBidiCaret()) {
        newOrientation = (event.keyLocation == SWT.LEFT) ? SWT.LEFT_TO_RIGHT : SWT.RIGHT_TO_LEFT;
      }
      handleKey(event);
    }
  }
}
