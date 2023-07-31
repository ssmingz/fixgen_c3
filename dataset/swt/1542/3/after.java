class PlaceHold {
  void computeDisplayTexts(GC gc) {
    int columnCount = parent.columns.length;
    if (columnCount == 0) {
      return;
    }
    for (int i = 0; i < columnCount; i++) {
      gc.setFont(getFont(i));
      computeDisplayText(i, gc);
    }
  }
}
