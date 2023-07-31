class PlaceHold {
  void setScrollWidth(TreeItem item) {
    if (drawCount != 0) {
      return;
    }
    TreeItem parentItem = item.parentItem;
    if ((parentItem != null) && (!parentItem.getExpanded())) {
      return;
    }
    GC gc = new GC(this);
    int newWidth = item.calculateWidth(gc);
    gc.dispose();
    short[] width = new short[1];
    OS.GetDataBrowserTableViewNamedColumnWidth(handle, COLUMN_ID, width);
    if (width[0] < newWidth) {
      OS.SetDataBrowserTableViewNamedColumnWidth(handle, COLUMN_ID, ((short) (newWidth)));
    }
  }
}
