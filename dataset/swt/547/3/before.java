class PlaceHold {
  public void select(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    checkItems();
    showItem(item);
    NSOutlineView outlineView = ((NSOutlineView) (view));
    int row = outlineView.rowForItem(item.handle);
    NSIndexSet set = ((NSIndexSet) (new NSIndexSet().alloc()));
    set = set.initWithIndex(row);
    ignoreSelect = true;
    outlineView.selectRowIndexes(set, false);
    ignoreSelect = false;
    set.release();
  }
}
