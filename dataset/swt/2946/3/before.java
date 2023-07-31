class PlaceHold {
  void addItem(TreeItem item, int index) {
    TreeItem[] newChildren = new TreeItem[items.length + 1];
    System.arraycopy(items, 0, newChildren, 0, index);
    newChildren[index] = item;
    System.arraycopy(items, index, newChildren, index + 1, items.length - index);
    items = newChildren;
    if (!item.isAvailable()) {
      if (isInViewport() && (items.length == 1)) {
        Rectangle bounds = getExpanderBounds();
        parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
      }
      return;
    }
    parent.makeAvailable(item);
    Rectangle bounds = item.getBounds(false);
    int rightX = bounds.x + bounds.width;
    parent.updateHorizontalBar(rightX, rightX);
    parent.updateVerticalBar();
    if (item.availableIndex < parent.topIndex) {
      parent.topIndex++;
      parent.getVerticalBar().setSelection(parent.topIndex);
      return;
    }
    parent.redrawFromItemDownwards(availableIndex);
  }
}
