class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int result = parent.kEventMouseDown(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    if ((style & SWT.DROP_DOWN) != 0) {
      if (OS.HIVIEW) {
        CGPoint pt = new CGPoint();
        OS.GetEventParameter(
            theEvent, kEventParamWindowMouseLocation, typeHIPoint, null, sizeof, null, pt);
        OS.HIViewConvertPoint(pt, 0, handle);
        CGRect rect = new CGRect();
        OS.HIViewGetBounds(handle, rect);
        int x = ((int) (pt.x));
        int width = ((int) (rect.width));
        if ((width - x) < 12) {
          OS.HIViewConvertPoint(pt, handle, parent.handle);
          Event event = new Event();
          event.detail = SWT.ARROW;
          event.x = ((int) (pt.x));
          event.y = ((int) (pt.y));
          postEvent(Selection, event);
        }
      } else {
        int sizeof = Point.sizeof;
        Point pt = new Point();
        OS.GetEventParameter(
            theEvent, kEventParamMouseLocation, typeQDPoint, null, sizeof, null, pt);
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
    }
    display.grabControl = null;
    display.runDeferredEvents();
    tracking = false;
    result = OS.CallNextEventHandler(nextHandler, theEvent);
    if (tracking) {
      if (OS.HIVIEW && (text.length() > 0)) {
        partCode = 0;
        if (labelHandle != 0) {
          redrawWidget(labelHandle, false);
        }
        if ((image != null) && (iconHandle != 0)) {
          OS.SetControlData(
              iconHandle,
              kControlEntireControl,
              kControlIconTransformTag,
              2,
              new short[] {((short) (0))});
          redrawWidget(iconHandle, false);
        }
      }
      Point outPt = new Point();
      OS.GetGlobalMouse(outPt);
      Rect rect = new Rect();
      int window = OS.GetControlOwner(handle);
      int x;
      int y;
      if (OS.HIVIEW) {
        CGPoint pt = new CGPoint();
        pt.x = outPt.h;
        pt.y = outPt.v;
        OS.HIViewConvertPoint(pt, 0, parent.handle);
        x = ((int) (pt.x));
        y = ((int) (pt.y));
        OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      } else {
        OS.GetControlBounds(parent.handle, rect);
        x = outPt.h - rect.left;
        y = outPt.v - rect.top;
        OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      }
      x -= rect.left;
      y -= rect.top;
      short[] button = new short[1];
      OS.GetEventParameter(
          theEvent, kEventParamMouseButton, typeMouseButton, null, 2, null, button);
      int chord = OS.GetCurrentEventButtonState();
      int modifiers = OS.GetCurrentEventKeyModifiers();
      parent.sendMouseEvent(
          MouseUp, button[0], true, chord, ((short) (x)), ((short) (y)), modifiers);
    }
    tracking = false;
    return result;
  }
}
