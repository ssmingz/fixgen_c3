class PlaceHold {
  public void remove(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    TableItem item = items[index];
    if (item != null) {
      item.release(false);
    }
    if (index != (itemCount - 1)) {
      fixSelection(index, false);
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    NSTableView widget = ((NSTableView) (view));
    setRedraw(false);
    widget.noteNumberOfRowsChanged();
    widget.tile();
    setRedraw(true);
    if (itemCount == 0) {
      setTableEmpty();
    }
  }
}
