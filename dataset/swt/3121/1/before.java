class PlaceHold {
  public TreeItem getTopItem() {
    checkWidget();
    Rect rect = new Rect();
    int y = getBorder() + getHeaderHeight();
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if (item != null) {
        int columnId = (columnCount == 0) ? column_id : columns[0].id;
        if (OS.GetDataBrowserItemPartBounds(
                handle, item.id, columnId, kDataBrowserPropertyEnclosingPart, rect)
            == OS.noErr) {
          if ((rect.top <= y) && (y <= rect.bottom)) {
            return item;
          }
        }
      }
    }
    return null;
  }
}
