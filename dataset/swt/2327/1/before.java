class PlaceHold {
  boolean setScrollWidth(TableItem item) {
    if (columnCount != 0) {
      return false;
    }
    if (drawCount != 0) {
      return false;
    }
    if (currentItem != null) {
      if (currentItem != item) {
        fixScrollWidth = true;
      }
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
