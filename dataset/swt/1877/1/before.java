class PlaceHold {
  void moveDown(CoolItem item, int x_root) {
    int oldRowIndex = findItem(item).y;
    if ((oldRowIndex == (items.length - 1)) && (items[oldRowIndex].length == 1)) {
      return;
    }
    int newRowIndex = (items[oldRowIndex].length == 1) ? oldRowIndex : oldRowIndex + 1;
    removeItemFromRow(item, oldRowIndex);
    Rectangle old = item.getBounds();
    redraw(old.x, old.y, MINIMUM_WIDTH, old.height, false);
    if (newRowIndex == items.length) {
      CoolItem[][] newRows = new CoolItem[items.length + 1][];
      System.arraycopy(items, 0, newRows, 0, items.length);
      int row = items.length;
      newRows[row] = new CoolItem[1];
      newRows[row][0] = item;
      items = newRows;
    } else {
      insertItemIntoRow(item, newRowIndex, x_root);
    }
    relayout();
  }
}
