class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if (cellFont == null) {
      if (font == null) {
        return;
      }
      cellFont = new Font[count];
    }
    Font oldFont = cellFont[index];
    if (oldFont == font) {
      return;
    }
    cellFont[index] = font;
    if ((oldFont != null) && oldFont.equals(font)) {
      return;
    }
    cached = true;
    NSTableView tableView = ((NSTableView) (parent.view));
    if (parent.columnCount == 0) {
      index = ((parent.style & SWT.CHECK) != 0) ? 1 : 0;
    } else {
      TableColumn column = parent.getColumn(index);
      index = ((int) (tableView.columnWithIdentifier(column.nsColumn)));
    }
    NSRect rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    tableView.setNeedsDisplayInRect(rect);
  }
}
