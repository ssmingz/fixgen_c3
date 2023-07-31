class PlaceHold {
  boolean setScrollWidth(TreeItem item) {
    if (ignoreRedraw || (drawCount != 0)) {
      return false;
    }
    if (columnCount != 0) {
      return false;
    }
    TreeItem parentItem = item.parentItem;
    if ((parentItem != null) && (!parentItem.getExpanded())) {
      return false;
    }
    GC gc = new GC(this);
    int newWidth = item.calculateWidth(0, gc);
    gc.dispose();
    int oldWidth = ((int) (firstColumn.width()));
    if (oldWidth < newWidth) {
      firstColumn.setWidth(newWidth);
      if ((horizontalBar != null) && (horizontalBar.view != null)) {
        redrawWidget(view, false);
      }
      return true;
    }
    return false;
  }
}
