class PlaceHold {
  Rectangle getCellBounds(int columnIndex) {
    int y = parent.getItemY(this);
    if (parent.columns.length == 0) {
      int textPaintWidth = textWidths[0] + (2 * MARGIN_TEXT);
      int width = (getTextX(0) + textPaintWidth) + parent.horizontalOffset;
      return new Rectangle(-parent.horizontalOffset, y, width, parent.itemHeight);
    }
    TreeColumn column = parent.columns[columnIndex];
    return new Rectangle(column.getX(), y, column.width, parent.itemHeight);
  }
}
