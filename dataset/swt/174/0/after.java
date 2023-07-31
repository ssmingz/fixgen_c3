class PlaceHold {
  void packColumns() {
    int columnCount = table1.getColumnCount();
    for (int i = 0; i < columnCount; i++) {
      TableColumn tableColumn = table1.getColumn(i);
      tableColumn.pack();
    }
  }
}
