class PlaceHold {
  void setFont(NSFont font) {
    super.setFont(font);
    if (!hooks(MeasureItem)) {
      float ascent = font.ascender();
      float descent = (-font.descender()) + font.leading();
      ((NSOutlineView) (view)).setRowHeight(((int) ((0.5F + ascent) + descent)) + 1);
    } else {
      view.setNeedsDisplay(true);
    }
  }
}
