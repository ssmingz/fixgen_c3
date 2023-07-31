class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (item.getParent() != this) {
      return;
    }
    if (!item.isAvailable()) {
      item.expandAncestors();
    }
    int visibleItemCount = (getClientArea().height - getHeaderHeight()) / itemHeight;
    int index = Math.min(item.availableIndex, availableItems.length - visibleItemCount);
    if (topIndex == index) {
      return;
    }
    update();
    int change = topIndex - index;
    topIndex = index;
    getVerticalBar().setSelection(topIndex);
    Rectangle clientArea = getClientArea();
    GC gc = new GC(this);
    gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, change * itemHeight);
    gc.dispose();
  }
}
