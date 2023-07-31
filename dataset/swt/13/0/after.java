class PlaceHold {
  public TableColumn getColumn(int index) {
    checkWidget();
    if (!((0 <= index) && (index < columnCount))) {
      error(ERROR_INVALID_RANGE);
    }
    return columns[index];
  }
}
