class PlaceHold {
  int xMouse(int type, int w, int client_data, int call_data, int continue_to_dispatch) {
    int xDisplay = display.xDisplay;
    int[] newX = new int[1];
    int[] newY = new int[1];
    int[] unused = new int[1];
    OS.XQueryPointer(xDisplay, window, unused, unused, newX, newY, unused, unused, unused);
    if ((oldX != newX[0]) || (oldY != newY[0])) {
      Rectangle[] oldRectangles = rectangles;
      boolean oldStippled = stippled;
      Rectangle[] rectsToErase = new Rectangle[rectangles.length];
      for (int i = 0; i < rectangles.length; i++) {
        Rectangle current = rectangles[i];
        rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
      }
      Event event = new Event();
      event.x = newX[0];
      event.y = newY[0];
      if ((style & SWT.RESIZE) != 0) {
        resizeRectangles(newX[0] - oldX, newY[0] - oldY);
        sendEvent(Resize, event);
        if (isDisposed()) {
          cancelled = true;
          return 1;
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
        if (cursorPos != null) {
          newX[0] = cursorPos.x;
          newY[0] = cursorPos.y;
        }
      } else {
        moveRectangles(newX[0] - oldX, newY[0] - oldY);
        sendEvent(Move, event);
        if (isDisposed()) {
          cancelled = true;
          return 1;
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
      oldX = newX[0];
      oldY = newY[0];
    }
    tracking = type != OS.ButtonRelease;
    return 0;
  }
}
