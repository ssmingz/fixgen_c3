class PlaceHold {
  NSSize cellSize(int id, int sel) {
    NSSize size = super.cellSize(id, sel);
    NSImage image = new NSCell(id).image();
    if (image != null) {
      size.width += imageBounds.width + IMAGE_GAP;
    }
    if (hooks(MeasureItem)) {
      int[] outValue = new int[1];
      OS.object_getInstanceVariable(id, SWT_ROW, outValue);
      int rowIndex = outValue[0];
      TableItem item = _getItem(((int) (rowIndex)));
      OS.object_getInstanceVariable(id, SWT_COLUMN, outValue);
      int tableColumn = outValue[0];
      int columnIndex = 0;
      for (int i = 0; i < columnCount; i++) {
        if (columns[i].nsColumn.id == tableColumn) {
          columnIndex = i;
          break;
        }
      }
      sendMeasureItem(item, columnIndex, size);
    }
    return size;
  }
}
