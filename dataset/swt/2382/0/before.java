class PlaceHold {
  public Rectangle getBounds(int index) {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (!((0 <= index) && (index < Math.max(1, parent.columnCount)))) {
      return new Rectangle(0, 0, 0, 0);
    }
    NSOutlineView outlineView = ((NSOutlineView) (parent.view));
    TreeColumn column = parent.getColumn(index);
    index = ((int) (outlineView.columnWithIdentifier(column.nsColumn)));
    NSRect rect = outlineView.frameOfCellAtColumn(index, outlineView.rowForItem(handle));
    return new Rectangle(
        ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
  }
}
