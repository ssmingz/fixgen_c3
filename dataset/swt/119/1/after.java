class PlaceHold {
  void onDispose() {
    if (isDisposed()) {
      return;
    }
    for (int i = 0; i < items.length; i++) {
      items[i].dispose(false);
    }
    for (int i = 0; i < columns.length; i++) {
      columns[i].dispose(false);
    }
    topIndex = availableItemsCount = horizontalOffset = 0;
    availableItems = items = selectedItems = null;
    columns = orderedColumns = null;
    focusItem = anchorItem = insertMarkItem = lastClickedItem = null;
    lastSelectionEvent = null;
    header = null;
    resizeColumn = sortColumn = null;
    expanderBounds = null;
  }
}
