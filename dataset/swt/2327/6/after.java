class PlaceHold {
  boolean setScrollWidth(TableItem item) {
    if (columnCount != 0) {
      return false;
    }
    if (currentItem != null) {
      if (currentItem != item) {
        fixScrollWidth = true;
      }
      return false;
    }
    if (!getDrawing()) {
      return false;
    }
    GC gc = new GC(this);
    int newWidth = item.calculateWidth(0, gc);
    gc.dispose();
    newWidth += getInsetWidth();
    short[] width = new short[1];
    OS.GetDataBrowserTableViewNamedColumnWidth(handle, column_id, width);
    if (width[0] < newWidth) {
      OS.SetDataBrowserTableViewNamedColumnWidth(handle, column_id, ((short) (newWidth)));
      return true;
    }
    return false;
  }
}
