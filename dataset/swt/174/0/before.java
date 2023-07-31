class PlaceHold {
  void packColumns() {
    for (int i = 0; i < TableTab.columnTitles.length; i++) {
      TableColumn tableColumn = table1.getColumn(i);
      tableColumn.pack();
    }
  }
}
