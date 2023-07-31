class PlaceHold {
  void computeDisplayTexts(GC gc) {
    int columnCount = parent.columns.length;
    if (columnCount == 0) {
      return;
    }
    Font oldFont = gc.getFont();
    for (int i = 0; i < columnCount; i++) {
      boolean fontChanged = false;
      Font font = getFont(i);
      if (!font.equals(oldFont)) {
        gc.setFont(font);
        fontChanged = true;
      }
      computeDisplayText(i, gc);
      if (fontChanged) {
        gc.setFont(oldFont);
      }
    }
  }
}
