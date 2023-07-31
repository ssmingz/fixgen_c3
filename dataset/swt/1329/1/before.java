class PlaceHold {
  void updateFont(GC gc) {
    if (font == null) {
      computeDisplayTexts(gc);
      recomputeTextWidths(gc);
    }
  }
}
