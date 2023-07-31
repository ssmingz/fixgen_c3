class PlaceHold {
  void updateFont(GC gc) {
    if (font == null) {
      computeDisplayTexts(gc);
      computeTextWidths(gc);
    }
  }
}
