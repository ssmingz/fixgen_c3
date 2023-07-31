class PlaceHold {
  void key(NSEvent nsEvent) {
    int nsType = ((int) (nsEvent.type()));
    int modifierFlags = nsEvent.modifierFlags();
    int nsKeyCode = nsEvent.keyCode();
    int keyCode = Display.translateKey(nsKeyCode);
    switch (nsType) {
      case OS.NSKeyDown:
      case OS.NSKeyUp:
        {
          Event event = new Event();
          event.keyCode = keyCode;
          int type = (nsType == OS.NSKeyDown) ? SWT.KeyDown : SWT.KeyUp;
          if (!setKeyState(event, type, nsEvent)) {
            break;
          }
          if (!sendKeyEvent(type, event)) {
            return;
          }
          break;
        }
      case OS.NSFlagsChanged:
        {
          int mask = 0;
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
            int type = ((mask & modifierFlags) != 0) ? SWT.KeyDown : SWT.KeyUp;
            Event event = new Event();
            event.keyCode = keyCode;
            setLocationMask(event, nsEvent);
            setInputState(event, nsEvent, type);
            if (!sendKeyEvent(type, event)) {
              return;
            }
          }
          break;
        }
    }
    int stepSize = ((modifierFlags & OS.NSControlKeyMask) != 0) ? STEPSIZE_SMALL : STEPSIZE_LARGE;
    int xChange = 0;
    int yChange = 0;
    switch (nsKeyCode) {
      case 53:
        cancelled = true;
        tracking = false;
        break;
      case 76:
      case 36:
        tracking = false;
        break;
      case 123:
        xChange = -stepSize;
        break;
      case 124:
        xChange = stepSize;
        break;
      case 126:
        yChange = -stepSize;
        break;
      case 125:
        yChange = stepSize;
        break;
    }
    if ((xChange != 0) || (yChange != 0)) {
      Rectangle[] oldRectangles = rectangles;
      Rectangle[] rectsToErase = new Rectangle[rectangles.length];
      for (int i = 0; i < rectangles.length; i++) {
        Rectangle current = rectangles[i];
        rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
      }
      Event event = new Event();
      int newX = oldX + xChange;
      int newY = oldY + yChange;
      event.x = newX;
      event.y = newY;
      Point cursorPos;
      if ((style & SWT.RESIZE) != 0) {
        resizeRectangles(xChange, yChange);
        inEvent = true;
        sendEvent(Resize, event);
        inEvent = false;
        if (isDisposed()) {
          cancelled = true;
          return;
        }
        boolean draw = false;
        if (rectangles != oldRectangles) {
          int length = rectangles.length;
          if (length != rectsToErase.length) {
            draw = true;
          } else {
            for (int i = 0; i < length; i++) {
              if (!rectangles[i].equals(rectsToErase[i])) {
                draw = true;
                break;
              }
            }
          }
        } else {
          draw = true;
        }
        if (draw) {
          drawRectangles(window, rectsToErase, true);
          drawRectangles(window, rectangles, false);
        }
        cursorPos = adjustResizeCursor(true);
      } else {
        moveRectangles(xChange, yChange);
        inEvent = true;
        sendEvent(Move, event);
        inEvent = false;
        if (isDisposed()) {
          cancelled = true;
          return;
        }
        boolean draw = false;
        if (rectangles != oldRectangles) {
          int length = rectangles.length;
          if (length != rectsToErase.length) {
            draw = true;
          } else {
            for (int i = 0; i < length; i++) {
              if (!rectangles[i].equals(rectsToErase[i])) {
                draw = true;
                break;
              }
            }
          }
        } else {
          draw = true;
        }
        if (draw) {
          drawRectangles(window, rectsToErase, true);
          drawRectangles(window, rectangles, false);
        }
        cursorPos = adjustMoveCursor();
      }
      if (cursorPos != null) {
        oldX = cursorPos.x;
        oldY = cursorPos.y;
      }
    }
  }
}
