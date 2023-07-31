class PlaceHold {
  public void setBackground(int index, Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if (cellBackground == null) {
      if (color == null) {
        return;
      }
      cellBackground = new Color[count];
    }
    Color oldColor = cellBackground[index];
    if (oldColor == color) {
      return;
    }
    cellBackground[index] = color;
    if ((oldColor != null) && oldColor.equals(color)) {
      return;
    }
    cached = true;
    NSTableView tableView = ((NSTableView) (parent.view));
    TableColumn column = parent.getColumn(index);
    index = ((int) (tableView.columnWithIdentifier(column.nsColumn)));
    NSRect rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    tableView.setNeedsDisplayInRect(rect);
  }
}
