class PlaceHold {
  public void setText(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (index == 0) {
      if (string.equals(text)) {
        return;
      }
      width = -1;
      super.setText(string);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 <= index) && (index < count)) {
      if (strings == null) {
        strings = new String[count];
      }
      if (string.equals(strings[index])) {
        return;
      }
      strings[index] = string;
    }
    cached = true;
    if (index == 0) {
      parent.setScrollWidth(this);
    }
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
