class PlaceHold {
  boolean setInputState(Event event, NSEvent nsEvent, int type) {
    if (nsEvent == null) {
      nsEvent = NSApplication.sharedApplication().currentEvent();
      if (nsEvent == null) {
        return true;
      }
    }
    long modifierFlags = nsEvent.modifierFlags();
    if ((modifierFlags & OS.NSAlternateKeyMask) != 0) {
      event.stateMask |= SWT.ALT;
    }
    if ((modifierFlags & OS.NSShiftKeyMask) != 0) {
      event.stateMask |= SWT.SHIFT;
    }
    if ((modifierFlags & OS.NSControlKeyMask) != 0) {
      event.stateMask |= SWT.CONTROL;
    }
    if ((modifierFlags & OS.NSCommandKeyMask) != 0) {
      event.stateMask |= SWT.COMMAND;
    }
    int state = OS.GetCurrentEventButtonState();
    if ((state & 0x1) != 0) {
      event.stateMask |= SWT.BUTTON1;
    }
    if ((state & 0x2) != 0) {
      event.stateMask |= SWT.BUTTON3;
    }
    if ((state & 0x4) != 0) {
      event.stateMask |= SWT.BUTTON2;
    }
    if ((state & 0x8) != 0) {
      event.stateMask |= SWT.BUTTON4;
    }
    if ((state & 0x10) != 0) {
      event.stateMask |= SWT.BUTTON5;
    }
    switch (type) {
      case SWT.MouseDown:
      case SWT.MouseDoubleClick:
        if (event.button == 1) {
          event.stateMask &= ~SWT.BUTTON1;
        }
        if (event.button == 2) {
          event.stateMask &= ~SWT.BUTTON2;
        }
        if (event.button == 3) {
          event.stateMask &= ~SWT.BUTTON3;
        }
        if (event.button == 4) {
          event.stateMask &= ~SWT.BUTTON4;
        }
        if (event.button == 5) {
          event.stateMask &= ~SWT.BUTTON5;
        }
        break;
      case SWT.MouseUp:
        if (event.button == 1) {
          event.stateMask |= SWT.BUTTON1;
        }
        if (event.button == 2) {
          event.stateMask |= SWT.BUTTON2;
        }
        if (event.button == 3) {
          event.stateMask |= SWT.BUTTON3;
        }
        if (event.button == 4) {
          event.stateMask |= SWT.BUTTON4;
        }
        if (event.button == 5) {
          event.stateMask |= SWT.BUTTON5;
        }
        break;
      case SWT.KeyDown:
      case SWT.Traverse:
        if (event.keyCode == SWT.ALT) {
          event.stateMask &= ~SWT.ALT;
        }
        if (event.keyCode == SWT.SHIFT) {
          event.stateMask &= ~SWT.SHIFT;
        }
        if (event.keyCode == SWT.CONTROL) {
          event.stateMask &= ~SWT.CONTROL;
        }
        if (event.keyCode == SWT.COMMAND) {
          event.stateMask &= ~SWT.COMMAND;
        }
        break;
      case SWT.KeyUp:
        if (event.keyCode == SWT.ALT) {
          event.stateMask |= SWT.ALT;
        }
        if (event.keyCode == SWT.SHIFT) {
          event.stateMask |= SWT.SHIFT;
        }
        if (event.keyCode == SWT.CONTROL) {
          event.stateMask |= SWT.CONTROL;
        }
        if (event.keyCode == SWT.COMMAND) {
          event.stateMask |= SWT.COMMAND;
        }
        break;
    }
    return true;
  }
}
