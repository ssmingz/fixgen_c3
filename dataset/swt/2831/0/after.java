class PlaceHold {
  public Rectangle getTextBounds(int index) {
    checkWidget();
    if (!parent.checkData(this)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (!((0 <= index) && (index < Math.max(1, parent.columnCount)))) {
      return new Rectangle(0, 0, 0, 0);
    }
    NSTableView tableView = ((NSTableView) (parent.view));
    Image image = (index == 0) ? this.image : images != null ? images[index] : null;
    if (parent.columnCount == 0) {
      index = ((parent.style & SWT.CHECK) != 0) ? 1 : 0;
    } else {
      TableColumn column = parent.getColumn(index);
      index = parent.indexOf(column.nsColumn);
    }
    NSRect rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    rect.x += Tree.TEXT_GAP;
    rect.width -= Tree.TEXT_GAP;
    if (image != null) {
      int offset = parent.imageBounds.width + Tree.IMAGE_GAP;
      rect.x += offset;
      rect.width -= offset;
    }
    return new Rectangle(
        ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
  }
}
