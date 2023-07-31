class PlaceHold {
  void packColumns(Tree tree) {
    if (multipleColumns.getSelection()) {
      int columnCount = tree.getColumnCount();
      for (int i = 0; i < columnCount; i++) {
        TreeColumn treeColumn = tree.getColumn(i);
        treeColumn.pack();
      }
    }
  }
}
