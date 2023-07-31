class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (item.parent != this) {
      return;
    }
    if (!item.isAvailable()) {
      item.parentItem.expandAncestors();
    }
    int visibleItemCount = (clientArea.height - getHeaderHeight()) / itemHeight;
    if (availableItemsCount < visibleItemCount) {
      return;
    }
    int index = Math.min(item.availableIndex, availableItemsCount - visibleItemCount);
    if (topIndex == index) {
      return;
    }
    update();
    int change = topIndex - index;
    topIndex = index;
    ScrollBar vBar = getVerticalBar();
    if (vBar != null) {
      vBar.setSelection(topIndex);
    }
    if (drawCount <= 0) {
      GC gc = new GC(this);
      gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, change * itemHeight);
      gc.dispose();
    }
  }
}
