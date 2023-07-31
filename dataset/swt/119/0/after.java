class PlaceHold {
  void onDispose() {
    if (isDisposed()) {
      return;
    }
    for (int i = 0; i < itemsCount; i++) {
      items[i].dispose(false);
    }
    for (int i = 0; i < columns.length; i++) {
      columns[i].dispose(false);
    }
    itemsCount = topIndex = horizontalOffset = 0;
    items = selectedItems = null;
    columns = orderedColumns = null;
    focusItem = anchorItem = lastClickedItem = null;
    lastSelectionEvent = null;
    header = null;
    resizeColumn = sortColumn = null;
  }
}
