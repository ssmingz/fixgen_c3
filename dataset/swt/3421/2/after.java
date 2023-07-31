class PlaceHold {
  void createItem(TableColumn column, int index) {
    TableColumn[] newColumns = new TableColumn[columns.length + 1];
    System.arraycopy(columns, 0, newColumns, 0, index);
    newColumns[index] = column;
    System.arraycopy(columns, index, newColumns, index + 1, columns.length - index);
    columns = newColumns;
    if (orderedColumns != null) {
      int insertIndex = 0;
      if (index > 0) {
        insertIndex = columns[index - 1].getOrderIndex() + 1;
      }
      TableColumn[] newOrderedColumns = new TableColumn[orderedColumns.length + 1];
      System.arraycopy(orderedColumns, 0, newOrderedColumns, 0, insertIndex);
      newOrderedColumns[insertIndex] = column;
      System.arraycopy(
          orderedColumns,
          insertIndex,
          newOrderedColumns,
          insertIndex + 1,
          orderedColumns.length - insertIndex);
      orderedColumns = newOrderedColumns;
    }
    for (int i = 0; i < itemsCount; i++) {
      items[i].addColumn(column);
    }
  }
}
