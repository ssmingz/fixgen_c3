class PlaceHold {
  void computeDisplayTexts(GC gc) {
    if (((parent.style & SWT.VIRTUAL) != 0) && (!cached)) {
      return;
    }
    int columnCount = parent.columns.length;
    if (columnCount == 0) {
      return;
    }
    for (int i = 0; i < columnCount; i++) {
      gc.setFont(getFont(i, false));
      computeDisplayText(i, gc);
    }
  }
}
