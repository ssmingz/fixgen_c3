class PlaceHold {
  Rectangle getFocusBounds() {
    int x = getTextX(0);
    TreeColumn[] columns = parent.columns;
    if (columns.length > 0) {
      int rightX = columns[0].getX() + columns[0].width;
      x = Math.min(x, rightX - 1);
    }
    int width;
    if (columns.length == 0) {
      width = textWidths[0] + (2 * MARGIN_TEXT);
    } else {
      TreeColumn column;
      if ((parent.style & SWT.FULL_SELECTION) != 0) {
        column = columns[columns.length - 1];
      } else {
        column = columns[0];
      }
      width = ((column.getX() + column.width) - x) - 1;
    }
    return new Rectangle(x, parent.getItemY(this) + 1, width, parent.itemHeight - 1);
  }
}
