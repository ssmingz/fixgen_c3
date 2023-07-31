class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    checkItems();
    showItem(item, false);
    NSOutlineView outlineView = ((NSOutlineView) (view));
    ((NSOutlineView) (view)).scrollRowToVisible(outlineView.rowForItem(item.handle));
  }
}
