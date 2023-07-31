class PlaceHold {
  void recomputeTextWidths(GC gc) {
    int validColumnCount = Math.max(1, parent.columns.length);
    textWidths = new int[validColumnCount];
    Font oldFont = gc.getFont();
    for (int i = 0; i < textWidths.length; i++) {
      String value = getDisplayText(i);
      if (value != null) {
        boolean fontChanged = false;
        Font font = getFont(i);
        if (!font.equals(oldFont)) {
          gc.setFont(font);
          fontChanged = true;
        }
        textWidths[i] = gc.stringExtent(value).x;
        if (fontChanged) {
          gc.setFont(oldFont);
        }
      }
    }
  }
}
