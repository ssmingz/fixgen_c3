class PlaceHold {
  void doDispose() {
    if (isDisposed()) {
      return;
    }
    for (int i = 0; i < items.length; i++) {
      items[i].dispose(false);
    }
    for (int i = 0; i < columns.length; i++) {
      columns[i].dispose(false);
    }
    availableItems = items = selectedItems = null;
    columns = null;
    focusItem = anchorItem = insertMarkItem = lastClickedItem = null;
    header = null;
    resizeColumn = null;
  }
}
