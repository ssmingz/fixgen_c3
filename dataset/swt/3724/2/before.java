class PlaceHold {
  void destroyItem(TreeItem item) {
    int count;
    TreeItem[] items;
    TreeItem parentItem = item.parentItem;
    if (parentItem != null) {
      count = parentItem.itemCount;
      items = parentItem.items;
    } else {
      count = this.itemCount;
      items = this.items;
    }
    int index = 0;
    while (index < count) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    System.arraycopy(items, index + 1, items, index, (--count) - index);
    items[count] = null;
    if (parentItem != null) {
      parentItem.itemCount = count;
    } else {
      this.itemCount = count;
    }
    NSOutlineView widget = ((NSOutlineView) (view));
    if (getDrawing()) {
      if (parentItem != null) {
        widget.reloadItem(parentItem.handle, true);
      } else {
        widget.reloadData();
      }
    } else {
      reloadPending = true;
    }
    setScrollWidth();
    if (this.itemCount == 0) {
      imageBounds = null;
    }
  }
}
