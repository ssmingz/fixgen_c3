class PlaceHold {
  void createItem(CoolItem item, int index) {
    int itemCount = getItemCount();
    int row = 0;
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    item.id = itemCount;
    if (items.length == 0) {
      items = new CoolItem[1][1];
      items[0][0] = item;
    } else {
      int i = index;
      if (index < itemCount) {
        while (i > items[row].length) {
          i -= items[row].length;
          row++;
        }
      } else {
        row = items.length - 1;
        i = items[row].length;
      }
      int oldLength = items[row].length;
      CoolItem[] newRow = new CoolItem[oldLength + 1];
      System.arraycopy(items[row], 0, newRow, 0, i);
      newRow[index] = item;
      System.arraycopy(items[row], i, newRow, i + 1, oldLength - i);
      items[row] = newRow;
    }
    item.requestedWidth = CoolItem.MINIMUM_WIDTH;
    relayout();
  }
}
