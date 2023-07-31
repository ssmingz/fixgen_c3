class PlaceHold {
  boolean setScrollWidth(boolean set, int[] childIds, boolean recurse) {
    if (ignoreRedraw || (!getDrawing())) {
      return false;
    }
    if ((columnCount != 0) || (childIds == null)) {
      return false;
    }
    GC gc = new GC(this);
    int newWidth = calculateWidth(childIds, gc, recurse, 0, 0);
    gc.dispose();
    newWidth += getInsetWidth(column_id, false);
    if (!set) {
      short[] width = new short[1];
      OS.GetDataBrowserTableViewNamedColumnWidth(handle, column_id, width);
      if (width[0] >= newWidth) {
        return false;
      }
    }
    OS.SetDataBrowserTableViewNamedColumnWidth(handle, column_id, ((short) (newWidth)));
    return true;
  }
}
