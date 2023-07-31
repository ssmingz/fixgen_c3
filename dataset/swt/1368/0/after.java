class PlaceHold {
  Event sendMeasureItemEvent(TableItem item, int row, int column, int hDC) {
    int hFont = (item.cellFont != null) ? item.cellFont[column] : -1;
    if (hFont == (-1)) {
      hFont = item.font;
    }
    RECT itemRect = item.getBounds(row, column, true, true, false, false, hDC);
    int nSavedDC = OS.SaveDC(hDC);
    GCData data = new GCData();
    data.device = display;
    data.hFont = hFont;
    GC gc = GC.win32_new(hDC, data);
    Event event = new Event();
    event.item = item;
    event.gc = gc;
    event.index = column;
    event.x = itemRect.left;
    event.y = itemRect.top;
    event.width = itemRect.right - itemRect.left;
    event.height = itemRect.bottom - itemRect.top;
    sendEvent(MeasureItem, event);
    event.gc = null;
    gc.dispose();
    OS.RestoreDC(hDC, nSavedDC);
    if ((!isDisposed()) && (!item.isDisposed())) {
      if (columnCount == 0) {
        int width = OS.SendMessage(handle, LVM_GETCOLUMNWIDTH, 0, 0);
        if ((event.x + event.width) > width) {
          OS.SendMessage(handle, LVM_SETCOLUMNWIDTH, 0, event.x + event.width);
        }
      }
      if (!ignoreItemHeight) {
        if (event.height > getItemHeight()) {
          setItemHeight(event.height);
        }
        ignoreItemHeight = true;
      }
    }
    return event;
  }
}
