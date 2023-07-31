class PlaceHold {
  public void setAlignment(int alignment) {
    checkWidget();
    if ((alignment & ((SWT.LEFT | SWT.RIGHT) | SWT.CENTER)) == 0) {
      return;
    }
    int index = parent.indexOf(this);
    if ((index == (-1)) || (index == 0)) {
      return;
    }
    style &= ~((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
    style |= alignment & ((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
    NSTableView tableView = ((NSTableView) (parent.view));
    NSTableHeaderView headerView = tableView.headerView();
    if (headerView == null) {
      return;
    }
    index = ((int) (tableView.columnWithIdentifier(nsColumn)));
    NSRect rect = headerView.headerRectOfColumn(index);
    headerView.setNeedsDisplayInRect(rect);
    rect = tableView.rectOfColumn(index);
    parent.view.setNeedsDisplayInRect(rect);
  }
}
