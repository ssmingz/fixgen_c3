class PlaceHold {
  LRESULT wmMouse(int message, int wParam, int lParam) {
    boolean isMirrored = (parent != null) && ((parent.style & SWT.MIRRORED) != 0);
    int newPos = OS.GetMessagePos();
    int newX = ((short) (newPos & 0xffff));
    int newY = ((short) (newPos >> 16));
    if ((newX != oldX) || (newY != oldY)) {
      Rectangle[] oldRectangles = rectangles;
      boolean oldStippled = stippled;
      Rectangle[] rectsToErase = new Rectangle[rectangles.length];
      for (int i = 0; i < rectangles.length; i++) {
        Rectangle current = rectangles[i];
        rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
      }
      Event event = new Event();
      event.x = newX;
      event.y = newY;
      if ((style & SWT.RESIZE) != 0) {
        if (isMirrored) {
          resizeRectangles(oldX - newX, newY - oldY);
        } else {
          resizeRectangles(newX - oldX, newY - oldY);
        }
        inEvent = true;
        sendEvent(Resize, event);
        inEvent = false;
        if (isDisposed()) {
          cancelled = true;
          return LRESULT.ONE;
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
          drawRectangles(rectsToErase, oldStippled);
          update();
          drawRectangles(rectangles, stippled);
        }
        Point cursorPos = adjustResizeCursor();
        newX = cursorPos.x;
        newY = cursorPos.y;
      } else {
        if (isMirrored) {
          moveRectangles(oldX - newX, newY - oldY);
        } else {
          moveRectangles(newX - oldX, newY - oldY);
        }
        inEvent = true;
        sendEvent(Move, event);
        inEvent = false;
        if (isDisposed()) {
          cancelled = true;
          return LRESULT.ONE;
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
          drawRectangles(rectsToErase, oldStippled);
          update();
          drawRectangles(rectangles, stippled);
        }
      }
      oldX = newX;
      oldY = newY;
    }
    tracking = message != OS.WM_LBUTTONUP;
    return null;
  }
}
