class PlaceHold {
  boolean setScrollWidth(boolean set, TreeItem[] items, boolean recurse) {
    if (items == null) {
      return false;
    }
    if (ignoreRedraw || (drawCount != 0)) {
      return false;
    }
    if (columnCount != 0) {
      return false;
    }
    GC gc = new GC(this);
    int newWidth = calculateWidth(items, 0, gc, recurse);
    gc.dispose();
    if (!set) {
      int oldWidth = ((int) (firstColumn.width()));
      if (oldWidth >= newWidth) {
        return false;
      }
    }
    firstColumn.setWidth(newWidth);
    return true;
  }
}
