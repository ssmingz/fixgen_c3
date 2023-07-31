class PlaceHold {
  public int indexOf(TreeColumn column) {
    checkWidget();
    if (column == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (columns == 0) {
      return -1;
    }
    int index = OS.GridViewColumnCollection_IndexOf(columns, column.handle);
    return index;
  }
}
