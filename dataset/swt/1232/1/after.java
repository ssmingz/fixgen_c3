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
    NSOutlineView outlineView = ((NSOutlineView) (parent.view));
    NSTableHeaderView headerView = outlineView.headerView();
    if (headerView == null) {
      return;
    }
    index = parent.indexOf(nsColumn);
    NSRect rect = headerView.headerRectOfColumn(index);
    headerView.setNeedsDisplayInRect(rect);
    rect = outlineView.rectOfColumn(index);
    parent.view.setNeedsDisplayInRect(rect);
  }
}
