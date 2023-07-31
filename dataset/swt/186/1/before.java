class PlaceHold {
  int XExposure(int w, int client_data, int call_data, int continue_to_dispatch) {
    if ((state & CANVAS) == 0) {
      return super.XExposure(w, client_data, call_data, continue_to_dispatch);
    }
    if ((!hooks(Paint)) && (!filters(Paint))) {
      return 0;
    }
    if ((style & SWT.NO_MERGE_PAINTS) != 0) {
      return super.XExposure(w, client_data, call_data, continue_to_dispatch);
    }
    XExposeEvent xEvent = new XExposeEvent();
    OS.memmove(xEvent, call_data, sizeof);
    int exposeCount = xEvent.count;
    if (exposeCount == 0) {
      if (OS.XEventsQueued(xEvent.display, QueuedAfterReading) != 0) {
        XAnyEvent xAnyEvent = new XAnyEvent();
        display.exposeCount = display.lastExpose = 0;
        int checkExposeProc = display.checkExposeProc;
        OS.XCheckIfEvent(xEvent.display, xAnyEvent, checkExposeProc, xEvent.window);
        exposeCount = display.exposeCount;
        int lastExpose = display.lastExpose;
        if ((exposeCount != 0) && (lastExpose != 0)) {
          XExposeEvent xExposeEvent = display.xExposeEvent;
          OS.memmove(xExposeEvent, lastExpose, sizeof);
          xExposeEvent.count = 0;
          OS.memmove(lastExpose, xExposeEvent, sizeof);
        }
      }
    }
    if ((exposeCount == 0) && (damagedRegion == 0)) {
      return super.XExposure(w, client_data, call_data, continue_to_dispatch);
    }
    if (damagedRegion == 0) {
      damagedRegion = OS.XCreateRegion();
    }
    OS.XtAddExposureToRegion(call_data, damagedRegion);
    if (exposeCount != 0) {
      return 0;
    }
    int xDisplay = OS.XtDisplay(handle);
    if (xDisplay == 0) {
      return 0;
    }
    Event event = new Event();
    GC gc = event.gc = new GC(this);
    Region region = Region.motif_new(damagedRegion);
    gc.setClipping(region);
    XRectangle rect = new XRectangle();
    OS.XClipBox(damagedRegion, rect);
    event.x = rect.x;
    event.y = rect.y;
    event.width = rect.width;
    event.height = rect.height;
    sendEvent(Paint, event);
    gc.dispose();
    event.gc = null;
    OS.XDestroyRegion(damagedRegion);
    damagedRegion = 0;
    return 0;
  }
}
