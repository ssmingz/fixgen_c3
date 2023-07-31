class PlaceHold {
  int gtk_mouse(int eventType, int widget, int eventPtr) {
    int[] newX = new int[1];
    int[] newY = new int[1];
    OS.gdk_window_get_pointer(window, newX, newY, null);
    if ((oldX != newX[0]) || (oldY != newY[0])) {
      Rectangle[] oldRectangles = rectangles;
      Rectangle[] rectsToErase = new Rectangle[rectangles.length];
      for (int i = 0; i < rectangles.length; i++) {
        Rectangle current = rectangles[i];
        rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
      }
      Event event = new Event();
      if (parent == null) {
        event.x = newX[0];
        event.y = newY[0];
      } else {
        Point screenCoord = display.map(parent, null, newX[0], newY[0]);
        event.x = screenCoord.x;
        event.y = screenCoord.y;
      }
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
          drawRectangles(rectsToErase);
          update();
          drawRectangles(rectangles);
        }
        Point cursorPos = adjustResizeCursor();
        newX[0] = cursorPos.x;
        newY[0] = cursorPos.y;
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
          drawRectangles(rectsToErase);
          update();
          drawRectangles(rectangles);
        }
      }
      oldX = newX[0];
      oldY = newY[0];
    }
    tracking = eventType != OS.GDK_BUTTON_RELEASE;
    return 0;
  }
}
