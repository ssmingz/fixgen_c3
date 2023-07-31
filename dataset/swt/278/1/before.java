class PlaceHold {
  void moveDown(CoolItem item, int x_root) {
    int oldRowIndex = findItem(item).y;
    boolean resize = false;
    if (items[oldRowIndex].length == 1) {
      resize = true;
      if (oldRowIndex == (items.length - 1)) {
        return;
      }
    }
    int newRowIndex = (items[oldRowIndex].length == 1) ? oldRowIndex : oldRowIndex + 1;
    removeItemFromRow(item, oldRowIndex, false);
    Rectangle old = item.getBounds();
    redraw(old.x, old.y, MINIMUM_WIDTH, old.height, false);
    if (newRowIndex == items.length) {
      CoolItem[][] newRows = new CoolItem[items.length + 1][];
      System.arraycopy(items, 0, newRows, 0, items.length);
      int row = items.length;
      newRows[row] = new CoolItem[1];
      newRows[row][0] = item;
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
