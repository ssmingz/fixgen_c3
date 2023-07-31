class PlaceHold {
  void flagsChanged(int id, int sel, int theEvent) {
    if (view.window().firstResponder().id == id) {
      if ((state & SAFARI_EVENTS_FIX) == 0) {
        Shell s = this.getShell();
        s.keyInputHappened = false;
        int mask = 0;
        NSEvent nsEvent = new NSEvent(theEvent);
        int modifiers = nsEvent.modifierFlags();
        int keyCode = Display.translateKey(nsEvent.keyCode());
        switch (keyCode) {
          case SWT.ALT:
            mask = OS.NSAlternateKeyMask;
            break;
          case SWT.CONTROL:
            mask = OS.NSControlKeyMask;
            break;
          case SWT.COMMAND:
            mask = OS.NSCommandKeyMask;
            break;
          case SWT.SHIFT:
            mask = OS.NSShiftKeyMask;
            break;
          case SWT.CAPS_LOCK:
            Event event = new Event();
            event.keyCode = keyCode;
            setInputState(event, nsEvent, KeyDown);
            sendKeyEvent(KeyDown, event);
            setInputState(event, nsEvent, KeyUp);
            sendKeyEvent(KeyUp, event);
            break;
        }
        if (mask != 0) {
          s.keyInputHappened = true;
          int type = ((mask & modifiers) != 0) ? SWT.KeyDown : SWT.KeyUp;
          if (type == SWT.KeyDown) {
            s.keyInputHappened = true;
          }
          Event event = new Event();
          event.keyCode = keyCode;
          setLocationMask(event, nsEvent);
          setInputState(event, nsEvent, type);
          if (!sendKeyEvent(type, event)) {
            return;
          }
        }
      }
    }
    super.flagsChanged(id, sel, theEvent);
  }
}
