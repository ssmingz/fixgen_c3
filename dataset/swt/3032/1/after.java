class PlaceHold {
  void updateFont(GC gc) {
    if (font == null) {
      computeDisplayTexts(gc);
      computeTextWidths(gc);
    }
    for (int i = 0; i < items.length; i++) {
      items[i].updateFont(gc);
    }
  }
}
