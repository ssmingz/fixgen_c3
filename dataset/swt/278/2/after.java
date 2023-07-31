class PlaceHold {
  void moveUp(CoolItem item, int x_root) {
    Point point = findItem(item);
    int oldRowIndex = point.y;
    boolean resize = false;
    if (items[oldRowIndex].length == 1) {
      resize = true;
      if (oldRowIndex == 0) {
        return;
      }
    }
    removeItemFromRow(item, oldRowIndex, false);
    Rectangle old = item.internalGetBounds();
    internalRedraw(old.x, old.y, MINIMUM_WIDTH, old.height);
    int newRowIndex = Math.max(0, oldRowIndex - 1);
    if (oldRowIndex == 0) {
      CoolItem[][] newRows = new CoolItem[items.length + 1][];
      System.arraycopy(items, 0, newRows, 1, items.length);
      newRows[0] = new CoolItem[1];
      newRows[0][0] = item;
      items = newRows;
      resize = true;
      item.wrap = true;
    } else {
      insertItemIntoRow(item, newRowIndex, x_root);
    }
    if (resize) {
      relayout();
    } else {
      layoutItems();
    }
  }
}
