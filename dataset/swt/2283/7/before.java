class PlaceHold {
  public void select(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    showItem(item);
    NSOutlineView outlineView = ((NSOutlineView) (view));
    int row = outlineView.rowForItem(item.handle);
    NSIndexSet indexes = new NSIndexSet(NSIndexSet.indexSetWithIndex(row));
    outlineView.selectRowIndexes(indexes, false);
  }
}
