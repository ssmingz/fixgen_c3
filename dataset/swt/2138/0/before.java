class PlaceHold {
  public void setSortDirection(int direction) {
    checkWidget();
    if (((direction != SWT.UP) && (direction != SWT.DOWN)) && (direction != SWT.NONE)) {
      return;
    }
    if (direction == sortDirection) {
      return;
    }
    sortDirection = direction;
    if (sortColumn == null) {
      return;
    }
    NSTableHeaderView headerView = ((NSTableView) (view)).headerView();
    if (headerView == null) {
      return;
    }
    int index = indexOf(sortColumn);
    if ((style & SWT.CHECK) != 0) {
      index++;
    }
    NSRect rect = headerView.headerRectOfColumn(index);
    headerView.setNeedsDisplayInRect(rect);
  }
}
