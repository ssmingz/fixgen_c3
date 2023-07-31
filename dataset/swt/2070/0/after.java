class PlaceHold {
  void removeItem(TreeItem item, int index) {
    if (isDisposed()) {
      return;
    }
    TreeItem[] newItems = new TreeItem[items.length - 1];
    System.arraycopy(items, 0, newItems, 0, index);
    System.arraycopy(items, index + 1, newItems, index, newItems.length - index);
    items = newItems;
    if ((items.length == 0) && (!parent.inExpand)) {
      expanded = false;
      Rectangle bounds = getExpanderBounds();
      parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
    }
  }
}
