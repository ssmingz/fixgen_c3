class PlaceHold {
  boolean sendMouseEvent(int type, short button, int theEvent) {
    Event event = new Event();
    event.type = type;
    Point pt = new Point();
    OS.GetEventParameter(
        theEvent, kEventParamMouseLocation, typeQDPoint, null, pt.sizeof, null, pt);
    Rect rect = new Rect();
    int window = OS.GetControlOwner(handle);
    OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
    event.x = pt.h - rect.left;
    event.y = pt.v - rect.top;
    OS.GetControlBounds(handle, rect);
    event.x -= rect.left;
    event.y -= rect.top;
    setInputState(event, theEvent);
    postEvent(type, event);
    return true;
  }
}
