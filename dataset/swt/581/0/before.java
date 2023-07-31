class PlaceHold {
  Event sendMeasureItemEvent(TableItem item, int row, int column, int hDC) {
    GCData data = new GCData();
    data.device = display;
    data.hFont = item.fontHandle(column);
    int nSavedDC = OS.SaveDC(hDC);
    GC gc = GC.win32_new(hDC, data);
    RECT itemRect = item.getBounds(row, column, true, true, false, false, hDC);
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
        int width = ((int) (OS.SendMessage(handle, LVM_GETCOLUMNWIDTH, 0, 0)));
        if ((event.x + event.width) > width) {
          OS.SendMessage(handle, LVM_SETCOLUMNWIDTH, 0, event.x + event.width);
        }
      }
      if (event.height > getItemHeight()) {
        setItemHeight(event.height);
      }
    }
    return event;
  }
}
