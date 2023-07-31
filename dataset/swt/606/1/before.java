class PlaceHold {
  int kEventRawKeyPressed(int nextHandler, int theEvent, int userData) {
    if (!sendKeyEvent(KeyDown, theEvent)) {
      return OS.noErr;
    }
    int[] keyCode = new int[1];
    OS.GetEventParameter(
        theEvent, kEventParamKeyCode, typeUInt32, null, keyCode.length * 4, null, keyCode);
    int[] modifiers = new int[1];
    OS.GetEventParameter(theEvent, kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
    int stepSize = ((modifiers[0] & OS.controlKey) != 0) ? STEPSIZE_SMALL : STEPSIZE_LARGE;
    int xChange = 0;
    int yChange = 0;
    switch (keyCode[0]) {
      case 53:
        cancelled = true;
        tracking = false;
        break;
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
          return OS.noErr;
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
          update();
          drawRectangles(window, rectangles, false);
        }
        cursorPos = adjustResizeCursor();
      } else {
        moveRectangles(xChange, yChange);
        inEvent = true;
        sendEvent(Move, event);
        inEvent = false;
        if (isDisposed()) {
          cancelled = true;
          return OS.noErr;
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
          update();
          drawRectangles(window, rectangles, false);
        }
        cursorPos = adjustMoveCursor();
      }
      oldX = cursorPos.x;
      oldY = cursorPos.y;
    }
    return 0;
  }
}
