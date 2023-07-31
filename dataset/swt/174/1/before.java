class PlaceHold {
  void packColumns(Tree tree) {
    if (multipleColumns.getSelection()) {
      for (int i = 0; i < TreeTab.columnTitles.length; i++) {
        TreeColumn treeColumn = tree.getColumn(i);
        treeColumn.pack();
      }
    }
  }
}
