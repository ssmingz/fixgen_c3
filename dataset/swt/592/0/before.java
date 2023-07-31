class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    NSOutlineView outlineView = ((NSOutlineView) (parent.view));
    int row = outlineView.rowForItem(handle);
    NSRect rect = outlineView.rectOfRow(row);
    rect = outlineView.convertRect_toView_(rect, parent.scrollView);
    Rectangle result =
        new Rectangle(
            ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
    return result;
  }
}
