class PlaceHold {
  void createItem(TableColumn column, int index) {
    if (!((0 <= index) && (index <= columnCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (columnCount == 0) {
      column.handle = OS.gtk_tree_view_get_column(handle, 0);
      column.modelIndex = 3;
    } else {
      createColumn(column, index);
    }
    if (columnCount == columns.length) {
      TableColumn[] newColumns = new TableColumn[columns.length + 4];
      System.arraycopy(columns, 0, newColumns, 0, columns.length);
      columns = newColumns;
    }
    System.arraycopy(columns, index, columns, index + 1, (columnCount++) - index);
    columns[index] = column;
  }
}
