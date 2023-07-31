class PlaceHold {
  public Rectangle getTextBounds(int index) {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (!((0 <= index) && (index < Math.max(1, parent.columnCount)))) {
      return new Rectangle(0, 0, 0, 0);
    }
    NSOutlineView outlineView = ((NSOutlineView) (parent.view));
    Image image = (index == 0) ? this.image : images != null ? images[index] : null;
    TreeColumn column = parent.getColumn(index);
    index = ((int) (outlineView.columnWithIdentifier(column.nsColumn)));
    NSRect rect = outlineView.frameOfCellAtColumn(index, outlineView.rowForItem(handle));
    if (image != null) {
      int imageWidth = image.getBounds().width;
      rect.x += imageWidth;
      rect.width -= imageWidth;
    }
    return new Rectangle(
        ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
  }
}
