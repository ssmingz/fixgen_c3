class PlaceHold {
  public int indexOf(TreeColumn column) {
    checkWidget();
    if (column == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int index = OS.GridViewColumnCollection_IndexOf(columns, column.handle);
    return index;
  }
}
