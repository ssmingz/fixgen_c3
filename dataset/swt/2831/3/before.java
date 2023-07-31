class PlaceHold {
  public Rectangle getImageBounds(int index) {
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
      index = ((int) (tableView.columnWithIdentifier(column.nsColumn)));
    }
    NSRect rect = tableView.frameOfCellAtColumn(index, parent.indexOf(this));
    rect.x += Tree.IMAGE_GAP;
    if (image != null) {
      rect.width = parent.imageBounds.width;
    } else {
      rect.width = 0;
    }
    return new Rectangle(
        ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
  }
}
