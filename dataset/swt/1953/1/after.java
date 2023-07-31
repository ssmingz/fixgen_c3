class PlaceHold {
  int gtk_expose_event(int widget, int eventPtr) {
    if ((state & OBSCURED) != 0) {
      return 0;
    }
    GdkEventExpose gdkEvent = new GdkEventExpose();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    GCData data = new GCData();
    data.damageRgn = gdkEvent.region;
    GC gc = GC.gtk_new(this, data);
    OS.gdk_gc_set_clip_region(gc.handle, gdkEvent.region);
    int selStart = selection.x;
    int selEnd = selection.y;
    if (selStart > selEnd) {
      selStart = selection.y;
      selEnd = selection.x;
    }
    selStart = selEnd = -1;
    layout.draw(gc, 0, 0, selStart, selEnd, null, null);
    if (hasFocus() && (focusIndex != (-1))) {
      Rectangle[] rects = getRectangles(focusIndex);
      for (int i = 0; i < rects.length; i++) {
        Rectangle rect = rects[i];
        gc.drawFocus(rect.x, rect.y, rect.width, rect.height);
      }
    }
    if (hooks(Paint) || filters(Paint)) {
      Event event = new Event();
      event.count = gdkEvent.count;
      event.x = gdkEvent.area_x;
      event.y = gdkEvent.area_y;
      event.width = gdkEvent.area_width;
      event.height = gdkEvent.area_height;
      event.gc = gc;
      sendEvent(Paint, event);
      event.gc = null;
    }
    gc.dispose();
    return 0;
  }
}
