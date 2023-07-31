class PlaceHold {
  int gtk_expose_event(int widget, int eventPtr) {
    if ((state & OBSCURED) != 0) {
      return 0;
    }
    if ((state & CANVAS) == 0) {
      return super.gtk_expose_event(widget, eventPtr);
    }
    if ((style & SWT.NO_MERGE_PAINTS) == 0) {
      return super.gtk_expose_event(widget, eventPtr);
    }
    if ((!hooks(Paint)) && (!filters(Paint))) {
      return 0;
    }
    GdkEventExpose gdkEvent = new GdkEventExpose();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    int[] rectangles = new int[1];
    int[] n_rectangles = new int[1];
    OS.gdk_region_get_rectangles(gdkEvent.region, rectangles, n_rectangles);
    GdkRectangle rect = new GdkRectangle();
    for (int i = 0; i < n_rectangles[0]; i++) {
      Event event = new Event();
      OS.memmove(rect, rectangles[0] + (i * GdkRectangle.sizeof), sizeof);
      event.x = rect.x;
      event.y = rect.y;
      event.width = rect.width;
      event.height = rect.height;
      if ((style & SWT.MIRRORED) != 0) {
        event.x = (getClientWidth() - event.width) - event.x;
      }
      int damageRgn = OS.gdk_region_new();
      OS.gdk_region_union_with_rect(damageRgn, rect);
      GCData data = new GCData();
      data.damageRgn = damageRgn;
      GC gc = event.gc = GC.gtk_new(this, data);
      OS.gdk_gc_set_clip_region(gc.handle, damageRgn);
      sendEvent(Paint, event);
      gc.dispose();
      OS.gdk_region_destroy(damageRgn);
      event.gc = null;
    }
    OS.g_free(rectangles[0]);
    return 0;
  }
}
