class PlaceHold {
  int indexOf(TreeColumn column) {
    checkWidget();
    if (column == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    for (int i = 0; i < columnCount; i++) {
      if (columns[i] == column) {
        return i;
      }
    }
    return -1;
  }
}
