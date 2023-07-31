class PlaceHold {
  public boolean open() {
    checkWidget();
    if (rectangles == null) {
      return false;
    }
    int xDisplay = display.xDisplay;
    int color = OS.XWhitePixel(xDisplay, 0);
    int xWindow = OS.XDefaultRootWindow(xDisplay);
    if (parent != null) {
      xWindow = OS.XtWindow(parent.handle);
      if (xWindow == 0) {
        return false;
      }
    }
    boolean cancelled = false;
    tracking = true;
    drawRectangles();
    XAnyEvent xEvent = new XAnyEvent();
    int[] unused = new int[1];
    int[] newX = new int[1];
    int[] newY = new int[1];
    int[] oldX = new int[1];
    int[] oldY = new int[1];
    int xtContext = OS.XtDisplayToApplicationContext(xDisplay);
    OS.XQueryPointer(xDisplay, xWindow, unused, unused, oldX, oldY, unused, unused, unused);
    while (tracking) {
      if ((parent != null) && parent.isDisposed()) {
        break;
      }
      OS.XtAppNextEvent(xtContext, xEvent);
      switch (xEvent.type) {
        case OS.ButtonRelease:
        case OS.MotionNotify:
          OS.XQueryPointer(xDisplay, xWindow, unused, unused, newX, newY, unused, unused, unused);
          if ((oldX[0] != newX[0]) || (oldY[0] != newY[0])) {
            drawRectangles();
            if ((style & SWT.RESIZE) != 0) {
              resizeRectangles(newX[0] - oldX[0], newY[0] - oldY[0]);
            } else {
              moveRectangles(newX[0] - oldX[0], newY[0] - oldY[0]);
            }
            Event event = new Event();
            event.x = newX[0];
            event.y = newY[0];
            if ((style | SWT.RESIZE) != 0) {
              sendEvent(Resize, event);
            } else {
              sendEvent(Move, event);
            }
            if (isDisposed()) {
              return false;
            }
            drawRectangles();
            oldX[0] = newX[0];
            oldY[0] = newY[0];
          }
          tracking = xEvent.type != OS.ButtonRelease;
          break;
        case OS.KeyPress:
          XKeyEvent keyEvent = new XKeyEvent();
          OS.memmove(keyEvent, xEvent, sizeof);
          if (keyEvent.keycode != 0) {
            int[] keysym = new int[1];
            OS.XLookupString(keyEvent, null, 0, keysym, null);
            keysym[0] &= 0xffff;
            int xChange = 0;
            int yChange = 0;
            int stepSize = ((keyEvent.state & OS.ControlMask) != 0) ? 2 : 10;
            switch (keysym[0]) {
              case OS.XK_Return:
                tracking = false;
                break;
              case OS.XK_Escape:
                tracking = false;
                cancelled = true;
                break;
              case OS.XK_Cancel:
                tracking = false;
                cancelled = true;
                break;
              case OS.XK_Left:
                xChange = -stepSize;
                break;
              case OS.XK_Right:
                xChange = stepSize;
                break;
              case OS.XK_Up:
                yChange = -stepSize;
                break;
              case OS.XK_Down:
                yChange = stepSize;
                break;
            }
            if ((xChange != 0) || (yChange != 0)) {
              drawRectangles();
              if ((style & SWT.RESIZE) != 0) {
                resizeRectangles(xChange, yChange);
              } else {
                moveRectangles(xChange, yChange);
              }
              Event event = new Event();
              event.x = oldX[0] + xChange;
              event.y = oldY[0] + yChange;
              if ((style | SWT.RESIZE) != 0) {
                sendEvent(Resize, event);
              } else {
                sendEvent(Move, event);
              }
              if (isDisposed()) {
                return false;
              }
              drawRectangles();
              Rectangle boundingRectangle = computeBounds();
              if ((style & SWT.RESIZE) != 0) {
                newX[0] = boundingRectangle.x + boundingRectangle.width;
                newY[0] = boundingRectangle.y + boundingRectangle.height;
              } else {
                newX[0] = boundingRectangle.x + (boundingRectangle.width / 2);
                newY[0] = boundingRectangle.y;
              }
              OS.XWarpPointer(xDisplay, NONE, xWindow, 0, 0, 0, 0, newX[0], newY[0]);
              OS.XQueryPointer(
                  xDisplay, xWindow, unused, unused, newX, newY, unused, unused, unused);
              oldX[0] = newX[0];
              oldY[0] = newY[0];
            }
          }
          break;
        default:
          OS.XtDispatchEvent(xEvent);
      }
    }
    drawRectangles();
    tracking = false;
    return !cancelled;
  }
}
