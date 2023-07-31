class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (!parent.checkData(this)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    NSTableView widget = ((NSTableView) (parent.view));
    int rowIndex = parent.indexOf(this);
    NSTableColumn column =
        (parent.columnCount == 0) ? parent.firstColumn : parent.columns[0].nsColumn;
    int columnIndex = parent.indexOf(column);
    NSRect titleRect = widget.frameOfCellAtColumn(columnIndex, rowIndex);
    if (image != null) {
      titleRect.x += parent.imageBounds.width + Table.IMAGE_GAP;
    }
    Font font = null;
    if (cellFont != null) {
      font = cellFont[columnIndex];
    }
    if (font == null) {
      font = this.font;
    }
    if (font == null) {
      font = parent.font;
    }
    if (font == null) {
      font = parent.defaultFont();
    }
    NSCell cell = parent.dataCell;
    cell.setImage(null);
    if (font.extraTraits != 0) {
      NSAttributedString attribStr = parent.createString(text, font, null, 0, true, false);
      cell.setAttributedStringValue(attribStr);
      attribStr.release();
    } else {
      cell.setFont(font.handle);
      cell.setTitle(NSString.stringWith(text));
    }
    NSSize size = cell.cellSize();
    NSRect columnRect = widget.rectOfColumn(columnIndex);
    size.width = Math.min(size.width, columnRect.width - (titleRect.x - columnRect.x));
    return new Rectangle(
        ((int) (titleRect.x)),
        ((int) (titleRect.y)),
        ((int) (Math.ceil(size.width))),
        ((int) (Math.ceil(titleRect.height))));
  }
}
