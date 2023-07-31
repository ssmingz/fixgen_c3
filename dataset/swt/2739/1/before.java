class PlaceHold {
  public void setInsertMark(TreeItem item, boolean before) {
    checkWidget();
    if ((item != null) && item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((item != null) && (item.getParent() != this)) {
      return;
    }
    if ((item == insertMarkItem) && (before == insertMarkPrecedes)) {
      return;
    }
    TreeItem oldInsertItem = insertMarkItem;
    insertMarkItem = item;
    insertMarkPrecedes = before;
    if (oldInsertItem != null) {
      redrawItem(oldInsertItem.availableIndex, true);
    }
    if ((item != null) && (item != oldInsertItem)) {
      redrawItem(item.availableIndex, true);
    }
  }
}
