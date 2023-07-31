class PlaceHold {
  int kEventMouse(int eventKind, int nextHandler, int theEvent, int userData) {
    int sizeof = Point.sizeof;
    Point where = new Point();
    OS.GetEventParameter(
        theEvent, kEventParamMouseLocation, typeQDPoint, null, sizeof, null, where);
    int newX = where.h;
    int newY = where.v;
    if ((newX != oldX) || (newY != oldY)) {
      Rectangle[] oldRectangles = rectangles;
      Rectangle[] rectsToErase = new Rectangle[rectangles.length];
      for (int i = 0; i < rectangles.length; i++) {
        Rectangle current = rectangles[i];
        rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
      }
      Event event = new Event();
      event.x = newX;
      event.y = newY;
      if ((style & SWT.RESIZE) != 0) {
        boolean orientationInit = resizeRectangles(newX - oldX, newY - oldY);
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
          drawRectangles(window, rectangles, false);
        }
        Point cursorPos = adjustResizeCursor(orientationInit);
        if (cursorPos != null) {
          newX = cursorPos.x;
          newY = cursorPos.y;
        }
      } else {
        moveRectangles(newX - oldX, newY - oldY);
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
          drawRectangles(window, rectangles, false);
        }
      }
      oldX = newX;
      oldY = newY;
    }
    tracking = eventKind != OS.kEventMouseUp;
    return 0;
  }
}
