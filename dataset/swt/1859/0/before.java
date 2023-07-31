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
    NSTableHeaderView headerView = ((NSTableView) (parent.view)).headerView();
    if (headerView == null) {
      return;
    }
    NSRect rect = headerView.headerRectOfColumn(index + 1);
    headerView.setNeedsDisplayInRect(rect);
  }
}
