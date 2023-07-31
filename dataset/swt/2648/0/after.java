class PlaceHold {
  void recomputeTextWidths(GC gc) {
    int validColumnCount = Math.max(1, parent.columns.length);
    textWidths = new int[validColumnCount];
    for (int i = 0; i < textWidths.length; i++) {
      String value = getDisplayText(i);
      if (value != null) {
        gc.setFont(getFont(i, false));
        textWidths[i] = gc.stringExtent(value).x;
      }
    }
  }
}
