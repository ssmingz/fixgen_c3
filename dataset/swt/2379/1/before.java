class PlaceHold {
  void createItem(TableItem item, int index) {
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (itemCount == items.length) {
      int length = (getDrawing()) ? items.length + 4 : Math.max(4, (items.length * 3) / 2);
      TableItem[] newItems = new TableItem[length];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = item;
    NSTableView widget = ((NSTableView) (view));
    setRedraw(false);
    widget.noteNumberOfRowsChanged();
    widget.tile();
    setRedraw(true);
    if (index != itemCount) {
      fixSelection(index, true);
    }
  }
}
