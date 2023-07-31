class PlaceHold {
  Rectangle getFocusBounds() {
    int x = 0;
    TableColumn[] columns = parent.columns;
    int[] columnOrder = parent.getColumnOrder();
    if ((parent.style & SWT.FULL_SELECTION) != 0) {
      int col0index = (columnOrder.length == 0) ? 0 : columnOrder[0];
      if (col0index == 0) {
        x = getTextX(0);
      } else {
        x = -parent.horizontalOffset;
      }
    } else {
      x = getTextX(0);
    }
    if (columns.length > 0) {
      int rightX = columns[0].getX() + columns[0].width;
      x = Math.min(x, rightX - 1);
    }
    int width;
    if (columns.length == 0) {
      width = textWidths[0] + (2 * MARGIN_TEXT);
    } else {
      TableColumn column;
      if ((parent.style & SWT.FULL_SELECTION) != 0) {
        column = columns[columnOrder[columnOrder.length - 1]];
      } else {
        column = columns[0];
      }
      width = ((column.getX() + column.width) - x) - 1;
    }
    return new Rectangle(x, parent.getItemY(this) + 1, width, parent.itemHeight - 1);
  }
}
