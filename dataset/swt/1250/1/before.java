class PlaceHold {
  int processPaint(Object callData) {
    MacControlEvent mce = ((MacControlEvent) (callData));
    GC gc = new GC(this);
    Rectangle r = gc.carbon_focus(mce.getDamageRegionHandle(), mce.getGCContext());
    if ((r == null) || (!r.isEmpty())) {
      if (((state & CANVAS) != 0) && ((style & SWT.NO_BACKGROUND) == 0)) {
        gc.fillRectangle(r);
      }
      if (hooks(Paint)) {
        Event event = new Event();
        event.gc = gc;
        event.x = r.x;
        event.y = r.y;
        event.width = r.width;
        event.height = r.height;
        sendEvent(Paint, event);
      }
    }
    gc.carbon_unfocus();
    if (!gc.isDisposed()) {
      gc.dispose();
    }
    return OS.noErr;
  }
}
