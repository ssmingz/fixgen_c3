class PlaceHold {
  public boolean open() {
    checkWidget();
    if (rectangles == null) {
      return false;
    }
    boolean cancelled = false;
    tracking = true;
    Event event = new Event();
    MSG msg = new MSG();
    drawRectangles();
    int oldPos = OS.GetMessagePos();
    int oldX = ((short) (oldPos & 0xffff));
    int oldY = ((short) (oldPos >> 16));
    while (tracking && (!cancelled)) {
      if ((parent != null) && parent.isDisposed()) {
        break;
      }
      OS.GetMessage(msg, 0, 0, 0);
      int message = msg.message;
      switch (message) {
        case OS.WM_LBUTTONUP:
        case OS.WM_MOUSEMOVE:
          int newPos = OS.GetMessagePos();
          int newX = ((short) (newPos & 0xffff));
          int newY = ((short) (newPos >> 16));
          if ((newX != oldX) || (newY != oldY)) {
            drawRectangles();
            if ((style & SWT.RESIZE) != 0) {
              resizeRectangles(newX - oldX, newY - oldY);
            } else {
              moveRectangles(newX - oldX, newY - oldY);
            }
            event.x = newX;
            event.y = newY;
            if ((style | SWT.RESIZE) != 0) {
              sendEvent(Resize, event);
            } else {
              sendEvent(Move, event);
            }
            if (isDisposed()) {
              return false;
            }
            drawRectangles();
            oldX = newX;
            oldY = newY;
          }
          tracking = msg.message != OS.WM_LBUTTONUP;
          break;
        case OS.WM_KEYDOWN:
          int stepSize = (OS.GetKeyState(VK_CONTROL) < 0) ? 2 : 10;
          int xChange = 0;
          int yChange = 0;
          switch (msg.wParam) {
            case OS.VK_ESCAPE:
              cancelled = true;
              tracking = false;
              break;
            case OS.VK_RETURN:
              tracking = false;
              break;
            case OS.VK_LEFT:
              xChange = -stepSize;
              break;
            case OS.VK_RIGHT:
              xChange = stepSize;
              break;
            case OS.VK_UP:
              yChange = -stepSize;
              break;
            case OS.VK_DOWN:
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
            newX = oldX + xChange;
            newY = oldY + yChange;
            event.x = newX;
            event.y = newY;
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
            newX = boundingRectangle.x + (boundingRectangle.width / 2);
            if ((style & SWT.RESIZE) != 0) {
              newY = boundingRectangle.y + (boundingRectangle.height / 2);
            } else {
              newY = boundingRectangle.y;
            }
            POINT pt = new POINT();
            pt.x = newX;
            pt.y = newY;
            if (parent != null) {
              OS.ClientToScreen(parent.handle, pt);
            }
            OS.SetCursorPos(pt.x, pt.y);
            oldX = pt.x;
            oldY = pt.y;
          }
          break;
      }
      if (tracking && (!cancelled)) {
        if ((OS.WM_KEYFIRST <= message) && (message <= OS.WM_KEYLAST)) {
          continue;
        }
        if ((OS.WM_MOUSEFIRST <= message) && (message <= OS.WM_MOUSELAST)) {
          continue;
        }
      }
      OS.DispatchMessage(msg);
    }
    drawRectangles();
    tracking = false;
    return !cancelled;
  }
}
