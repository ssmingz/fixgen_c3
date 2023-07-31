class PlaceHold {
  public void showColumn(TreeColumn column) {
    checkWidget();
    if (column == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (column.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (column.parent != this) {
      return;
    }
    if (columnCount <= 1) {
      return;
    }
    int index = indexOf(column.nsColumn);
    if (!((0 <= index) && (index < (columnCount + ((style & SWT.CHECK) != 0 ? 1 : 0))))) {
      return;
    }
    ((NSOutlineView) (view)).scrollColumnToVisible(index);
  }
}
