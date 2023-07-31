class PlaceHold {
  void destroyItem(TableColumn column) {
    headerHideToolTip();
    int index = column.getIndex();
    int orderedIndex = column.getOrderIndex();
    TableColumn[] newColumns = new TableColumn[columns.length - 1];
    System.arraycopy(columns, 0, newColumns, 0, index);
    System.arraycopy(columns, index + 1, newColumns, index, newColumns.length - index);
    columns = newColumns;
    if (orderedColumns != null) {
      if (columns.length < 2) {
        orderedColumns = null;
      } else {
        int removeIndex = column.getOrderIndex();
        TableColumn[] newOrderedColumns = new TableColumn[orderedColumns.length - 1];
        System.arraycopy(orderedColumns, 0, newOrderedColumns, 0, removeIndex);
        System.arraycopy(
            orderedColumns,
            removeIndex + 1,
            newOrderedColumns,
            removeIndex,
            newOrderedColumns.length - removeIndex);
        orderedColumns = newOrderedColumns;
      }
    }
    if ((index == 0) && (columns.length > 0)) {
      columns[0].style |= SWT.LEFT;
      columns[0].style &= ~(SWT.CENTER | SWT.RIGHT);
    }
    for (int i = 0; i < itemsCount; i++) {
      items[i].removeColumn(column, index);
    }
    int lastColumnIndex = columns.length - 1;
    if (lastColumnIndex < 0) {
      updateHorizontalBar();
    } else {
      int newWidth = 0;
      for (int i = 0; i < columns.length; i++) {
        newWidth += columns[i].width;
      }
      ScrollBar hBar = getHorizontalBar();
      hBar.setMaximum(newWidth);
      hBar.setVisible(clientArea.width < newWidth);
      int selection = hBar.getSelection();
      if (selection != horizontalOffset) {
        horizontalOffset = selection;
        redraw();
        if (header.isVisible() && (drawCount == 0)) {
          header.redraw();
        }
      }
    }
    TableColumn[] orderedColumns = getOrderedColumns();
    for (int i = orderedIndex; i < orderedColumns.length; i++) {
      if (!orderedColumns[i].isDisposed()) {
        orderedColumns[i].sendEvent(Move);
      }
    }
    if (sortColumn == column) {
      sortColumn = null;
    }
  }
}
