class PlaceHold {
  public Rectangle getBounds(int index) {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    NSTableView tableView = ((NSTableView) (parent.view));
    if ((parent.style & SWT.CHECK) != 0) {
      index++;
    }
    NSRect rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    rect = tableView.convertRect_toView_(rect, parent.scrollView);
    Rectangle result =
        new Rectangle(
            ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
    return result;
  }
}
