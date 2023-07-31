class PlaceHold {
  boolean setScrollWidth(TreeItem item) {
    if (ignoreRedraw || (drawCount != 0)) {
      return false;
    }
    if (columnCount != 0) {
      return false;
    }
    TreeItem parentItem = item.parentItem;
    if ((parentItem != null) && (!parentItem._getExpanded())) {
      return false;
    }
    GC gc = new GC(this);
    int newWidth = item.calculateWidth(0, gc);
    gc.dispose();
    newWidth += getInsetWidth(column_id, false);
    short[] width = new short[1];
    OS.GetDataBrowserTableViewNamedColumnWidth(handle, column_id, width);
    if (width[0] < newWidth) {
      OS.SetDataBrowserTableViewNamedColumnWidth(handle, column_id, ((short) (newWidth)));
      return true;
    }
    return false;
  }
}
