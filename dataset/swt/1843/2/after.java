class PlaceHold {
  void updateColumnWidth(TableColumn column, GC gc) {
    int columnIndex = column.getIndex();
    gc.setFont(getFont(columnIndex));
    computeDisplayText(columnIndex, gc);
    textWidths[columnIndex] = gc.stringExtent(getDisplayText(columnIndex)).x;
  }
}
