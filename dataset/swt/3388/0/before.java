class PlaceHold {
  void createItem(TreeColumn column, int index) {
    TreeColumn[] newColumns = new TreeColumn[columns.length + 1];
    System.arraycopy(columns, 0, newColumns, 0, index);
    newColumns[index] = column;
    System.arraycopy(columns, index, newColumns, index + 1, columns.length - index);
    columns = newColumns;
    if (orderedColumns != null) {
      int insertIndex = 0;
      if (index > 0) {
        insertIndex = columns[index - 1].getOrderIndex() + 1;
      }
      TreeColumn[] newOrderedColumns = new TreeColumn[orderedColumns.length + 1];
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
    if (columns.length == 1) {
      column.itemImageWidth = orderedCol0imageWidth;
    } else if (column.getOrderIndex() == 0) {
      orderedCol0imageWidth = 0;
    }
    for (int i = 0; i < items.length; i++) {
      items[i].addColumn(column);
    }
  }
}
