class PlaceHold {
  int gtk_key_press_event(int widget, int eventPtr) {
    int result = super.gtk_key_press_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    GdkEventKey keyEvent = new GdkEventKey();
    OS.memmove(keyEvent, eventPtr, sizeof);
    int stepSize = ((keyEvent.state & OS.GDK_CONTROL_MASK) != 0) ? STEPSIZE_SMALL : STEPSIZE_LARGE;
    int xChange = 0;
    int yChange = 0;
    switch (keyEvent.keyval) {
      case OS.GDK_Escape:
        cancelled = true;
      case OS.GDK_Return:
        tracking = false;
        break;
      case OS.GDK_Left:
        xChange = -stepSize;
        break;
      case OS.GDK_Right:
        xChange = stepSize;
        break;
      case OS.GDK_Up:
        yChange = -stepSize;
        break;
      case OS.GDK_Down:
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
      event.x = oldX + xChange;
      event.y = oldY + yChange;
      if ((parent != null) && ((parent.style & SWT.MIRRORED) != 0)) {
        event.x = (parent.getClientWidth() - event.width) - event.x;
      }
      if ((style & SWT.RESIZE) != 0) {
        resizeRectangles(xChange, yChange);
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
        if (cursorPos != null) {
          oldX = cursorPos.x;
          oldY = cursorPos.y;
        }
      } else {
        moveRectangles(xChange, yChange);
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
        Point cursorPos = adjustMoveCursor();
        if (cursorPos != null) {
          oldX = cursorPos.x;
          oldY = cursorPos.y;
        }
      }
    }
    return result;
  }
}
