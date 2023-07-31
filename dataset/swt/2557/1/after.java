class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int status = super.kEventMouseDown(nextHandler, theEvent, userData);
    if (status == OS.noErr) {
      return status;
    }
    display.grabControl = null;
    display.runDeferredEvents();
    dragging = tracking = false;
    status = OS.CallNextEventHandler(nextHandler, theEvent);
    if (dragging) {
      Event event = new Event();
      sendEvent(Selection, event);
    }
    dragging = false;
    if (tracking) {
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
        OS.HIViewConvertPoint(pt, 0, handle);
        x = ((int) (pt.x));
        y = ((int) (pt.y));
        OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      } else {
        OS.GetControlBounds(handle, rect);
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
      sendMouseEvent(
          MouseUp, button[0], clickCount, true, chord, ((short) (x)), ((short) (y)), modifiers);
    }
    tracking = false;
    return status;
  }
}
