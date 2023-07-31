class PlaceHold {
  void createItem(TreeColumn column, int index) {
    if (!((0 <= index) && (index <= columnCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (index == 0) {
      column.style &= ~((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
      column.style |= SWT.LEFT;
    }
    if (columnCount == columns.length) {
      TreeColumn[] newColumns = new TreeColumn[columnCount + 4];
      System.arraycopy(columns, 0, newColumns, 0, columns.length);
      columns = newColumns;
    }
    NSTableColumn nsColumn;
    if (columnCount == 0) {
      nsColumn = firstColumn;
      nsColumn.setMinWidth(0);
      nsColumn.setResizingMask(NSTableColumnUserResizingMask);
      firstColumn = null;
    } else {
      NSOutlineView outlineView = ((NSOutlineView) (view));
      NSString str = NSString.stringWith("");
      nsColumn = ((NSTableColumn) (new NSTableColumn().alloc()));
      nsColumn = nsColumn.initWithIdentifier(NSString.stringWith(String.valueOf(++NEXT_ID)));
      nsColumn.setMinWidth(0);
      nsColumn.headerCell().setTitle(str);
      outlineView.addTableColumn(nsColumn);
      int checkColumn = ((style & SWT.CHECK) != 0) ? 1 : 0;
      outlineView.moveColumn(columnCount + checkColumn, index + checkColumn);
      nsColumn.setDataCell(dataCell);
      if (index == 0) {
        outlineView.setOutlineTableColumn(nsColumn);
      }
    }
    column.createJNIRef();
    NSTableHeaderCell headerCell = ((NSTableHeaderCell) (new SWTTableHeaderCell().alloc().init()));
    nsColumn.setHeaderCell(headerCell);
    display.addWidget(headerCell, column);
    column.nsColumn = nsColumn;
    nsColumn.setWidth(0);
    System.arraycopy(columns, index, columns, index + 1, (columnCount++) - index);
    columns[index] = column;
    for (int i = 0; i < itemCount; i++) {
      TreeItem item = items[i];
      if (item != null) {
        if (columnCount > 1) {
          createColumn(item, index);
        }
      }
    }
  }
}
