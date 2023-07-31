class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    showItem(item, false);
    NSOutlineView outlineView = ((NSOutlineView) (view));
    ((NSTableView) (view)).scrollRowToVisible(outlineView.rowForItem(item.handle));
  }
}
