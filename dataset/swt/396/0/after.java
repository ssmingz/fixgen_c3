class PlaceHold {
  int gtk_expose_event(int widget, int eventPtr) {
    if ((state & OBSCURED) != 0) {
      return 0;
    }
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
    if ((style & SWT.MIRRORED) != 0) {
      event.x = (getClientWidth() - event.width) - event.x;
    }
    GCData data = new GCData();
    data.damageRgn = gdkEvent.region;
    GC gc = event.gc = GC.gtk_new(this, data);
    OS.gdk_gc_set_clip_region(gc.handle, gdkEvent.region);
    sendEvent(Paint, event);
    gc.dispose();
    event.gc = null;
    return 0;
  }
}
