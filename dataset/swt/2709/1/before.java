class PlaceHold {
  void updateFont(GC gc) {
    if (font == null) {
      recomputeTextWidths(gc);
    }
    for (int i = 0; i < items.length; i++) {
      items[i].updateFont(gc);
    }
  }
}
