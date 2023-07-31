class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    NSTableView tableView = ((NSTableView) (parent.view));
    NSRect rect = tableView.rectOfRow(parent.indexOf(this));
    rect = tableView.convertRect_toView_(rect, parent.scrollView);
    Rectangle result =
        new Rectangle(
            ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
    return result;
  }
}
