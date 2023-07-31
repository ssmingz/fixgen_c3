class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int result = parent.kEventMouseDown(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    if ((style & SWT.DROP_DOWN) != 0) {
      int sizeof = Point.sizeof;
      Point pt = new Point();
      OS.GetEventParameter(theEvent, kEventParamMouseLocation, typeQDPoint, null, sizeof, null, pt);
      Rect rect = new Rect();
      int window = OS.GetControlOwner(handle);
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      int x = pt.h - rect.left;
      int y = pt.v - rect.top;
      OS.GetControlBounds(handle, rect);
      x -= rect.left;
      y -= rect.top;
      int width = rect.right - rect.left;
      if ((width - x) < 12) {
        x = rect.left;
        y = rect.bottom;
        OS.GetControlBounds(parent.handle, rect);
        x -= rect.left;
        y -= rect.top;
        Event event = new Event();
        event.detail = SWT.ARROW;
        event.x = x;
        event.y = y;
        postEvent(Selection, event);
      }
    }
    display.grabControl = null;
    display.runDeferredEvents();
    tracking = false;
    result = OS.CallNextEventHandler(nextHandler, theEvent);
    if (tracking) {
      Point outPt = new Point();
      OS.GetGlobalMouse(outPt);
      Rect rect = new Rect();
      int window = OS.GetControlOwner(handle);
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      int x = outPt.h - rect.left;
      int y = outPt.v - rect.top;
      OS.GetControlBounds(parent.handle, rect);
      x -= rect.left;
      y -= rect.top;
      short[] button = new short[1];
      OS.GetEventParameter(
          theEvent, kEventParamMouseButton, typeMouseButton, null, 2, null, button);
      int chord = OS.GetCurrentEventButtonState();
      int modifiers = OS.GetCurrentEventKeyModifiers();
      parent.sendMouseEvent(
          MouseUp, button[0], chord, ((short) (x)), ((short) (y)), modifiers, false);
    }
    tracking = false;
    return result;
  }
}
