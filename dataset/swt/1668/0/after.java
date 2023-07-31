class PlaceHold {
  int gtk_expose_event(int widget, int eventPtr) {
    if ((!hooks(Paint)) && (!filters(Paint))) {
      return 0;
    }
    GdkEventExpose gdkEvent = new GdkEventExpose();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    Event event = new Event();
    event.count = gdkEvent.count;
    event.x = gdkEvent.area_x;
    event.y = gdkEvent.area_y;
    event.width = gdkEvent.area_width;
    event.height = gdkEvent.area_height;
    GC gc = event.gc = new GC(this);
    Region region = Region.gtk_new(display, gdkEvent.region);
    gc.setClipping(region);
    sendEvent(Paint, event);
    gc.dispose();
    event.gc = null;
    return 0;
  }
}
