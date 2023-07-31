class PlaceHold {
  Event sendMeasureItemEvent(TreeItem item, int index, int hDC) {
    RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
    int nSavedDC = OS.SaveDC(hDC);
    GCData data = new GCData();
    data.device = display;
    data.hFont = item.fontHandle(index);
    GC gc = GC.win32_new(hDC, data);
    Event event = new Event();
    event.item = item;
    event.gc = gc;
    event.index = index;
    event.x = itemRect.left;
    event.y = itemRect.top;
    event.width = itemRect.right - itemRect.left;
    event.height = itemRect.bottom - itemRect.top;
    sendEvent(MeasureItem, event);
    event.gc = null;
    gc.dispose();
    OS.RestoreDC(hDC, nSavedDC);
    if (isDisposed() || item.isDisposed()) {
      return null;
    }
    if (hwndHeader != 0) {
      int count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
      if (count == 0) {
        if ((event.x + event.width) > scrollWidth) {
          setScrollWidth(scrollWidth = event.x + event.width);
        }
      }
    }
    if (event.height > getItemHeight()) {
      setItemHeight(event.height);
    }
    return event;
  }
}
