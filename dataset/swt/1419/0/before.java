class PlaceHold {
  Rectangle getFocusBounds() {
    int x = 0;
    int[] columnOrder = parent.getColumnOrder();
    if ((parent.style & SWT.FULL_SELECTION) != 0) {
      int col0index = (columnOrder.length == 0) ? 0 : columnOrder[0];
      if (col0index == 0) {
        x = getTextX(0);
      } else {
        x = 0;
      }
    } else {
      x = getTextX(0);
    }
    int width;
    TableColumn[] columns = parent.columns;
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
