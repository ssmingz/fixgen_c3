class PlaceHold {
  NSSize cellSize(int id, int sel) {
    NSSize size = super.cellSize(id, sel);
    NSCell cell = new NSCell(id);
    NSImage image = cell.image();
    if (image != null) {
      size.width += imageBounds.width + IMAGE_GAP;
    }
    if (hooks(MeasureItem)) {
      int[] outValue = new int[1];
      OS.object_getInstanceVariable(id, SWT_ROW, outValue);
      TreeItem item = ((TreeItem) (display.getWidget(outValue[0])));
      OS.object_getInstanceVariable(id, SWT_COLUMN, outValue);
      int tableColumn = outValue[0];
      int columnIndex = 0;
      for (int i = 0; i < columnCount; i++) {
        if (columns[i].nsColumn.id == tableColumn) {
          columnIndex = i;
          break;
        }
      }
      sendMeasureItem(item, cell.isHighlighted(), columnIndex, size);
    }
    return size;
  }
}
