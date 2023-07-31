class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int result = super.kEventMouseDown(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    Rect rect = new Rect();
    OS.GetControlBounds(handle, rect);
    int startX = rect.left;
    int startY = rect.top;
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    OS.GetControlBounds(handle, rect);
    Event event = new Event();
    event.x = startX -= rect.left;
    event.y = startY -= rect.top;
    event.width = width;
    event.height = height;
    sendEvent(Selection, event);
    if (isDisposed()) {
      return result;
    }
    if (!event.doit) {
      return result;
    }
    int offsetX;
    int offsetY;
    int window = OS.GetControlOwner(handle);
    if (OS.HIVIEW) {
      CGPoint pt = new CGPoint();
      OS.GetEventParameter(
          theEvent, kEventParamWindowMouseLocation, typeHIPoint, null, sizeof, null, pt);
      OS.HIViewConvertPoint(pt, 0, handle);
      offsetX = ((int) (pt.x));
      offsetY = ((int) (pt.y));
    } else {
      int sizeof = Point.sizeof;
      Point pt = new Point();
      OS.GetEventParameter(theEvent, kEventParamMouseLocation, typeQDPoint, null, sizeof, null, pt);
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      offsetX = pt.h - rect.left;
      offsetY = pt.v - rect.top;
      OS.GetControlBounds(handle, rect);
      offsetX -= rect.left;
      offsetY -= rect.top;
    }
    int port = (OS.HIVIEW) ? -1 : OS.GetWindowPort(window);
    int[] outModifiers = new int[1];
    short[] outResult = new short[1];
    CGPoint pt = new CGPoint();
    Point outPt = new Point();
    while (outResult[0] != OS.kMouseTrackingMouseUp) {
      OS.TrackMouseLocationWithOptions(
          port, 0, kEventDurationForever, outPt, outModifiers, outResult);
      switch (outResult[0]) {
        case OS.kMouseTrackingMouseDown:
        case OS.kMouseTrackingMouseUp:
        case OS.kMouseTrackingMouseDragged:
          {
            int x;
            int y;
            if (OS.HIVIEW) {
              OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
              pt.x = outPt.h - rect.left;
              pt.y = outPt.v - rect.top;
              OS.HIViewConvertPoint(pt, 0, handle);
              x = ((int) (pt.x));
              y = ((int) (pt.y));
            } else {
              OS.GetControlBounds(handle, rect);
              x = outPt.h - rect.left;
              y = outPt.v - rect.top;
            }
            int newX = startX;
            int newY = startY;
            if ((style & SWT.VERTICAL) != 0) {
              int clientWidth = rect.right - rect.left;
              newX = Math.min(Math.max(0, x - offsetX), clientWidth - width);
            } else {
              int clientHeight = rect.bottom - rect.top;
              newY = Math.min(Math.max(0, y - offsetY), clientHeight - height);
            }
            event = new Event();
            event.x = newX;
            event.y = newY;
            event.width = width;
            event.height = height;
            sendEvent(Selection, event);
            if (isDisposed()) {
              return result;
            }
            if (event.doit) {
              setBounds(event.x, event.y, width, height);
              if (isDisposed()) {
                return result;
              }
              parent.update(true);
            }
            break;
          }
        default:
          outResult[0] = OS.kMouseTrackingMouseUp;
          break;
      }
    }
    return OS.noErr;
  }
}
