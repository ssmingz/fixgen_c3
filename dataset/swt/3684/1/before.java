class PlaceHold {
  void addItem(TreeItem item, int index) {
    TreeItem[] newChildren = new TreeItem[items.length + 1];
    System.arraycopy(items, 0, newChildren, 0, index);
    newChildren[index] = item;
    System.arraycopy(items, index, newChildren, index + 1, items.length - index);
    items = newChildren;
    if (!item.isAvailable()) {
      if (isAvailable() && (items.length == 1)) {
        Rectangle bounds = getExpanderBounds();
        parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
      }
      return;
    }
    parent.makeAvailable(item);
    Rectangle bounds = item.getBounds();
    int rightX = bounds.x + bounds.width;
    parent.updateHorizontalBar(rightX, rightX);
    ScrollBar vBar = parent.getVerticalBar();
    vBar.setMaximum(vBar.getMaximum() + 1);
    if (item.availableIndex < parent.topIndex) {
      parent.topIndex++;
      vBar.setSelection(parent.topIndex);
      return;
    }
    parent.redrawFromItemDownwards(availableIndex);
  }
}
