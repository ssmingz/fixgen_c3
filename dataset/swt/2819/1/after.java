class PlaceHold {
  void setFont(NSFont font) {
    super.setFont(font);
    if (!hooks(MeasureItem)) {
      setItemHeight(null, font);
    } else {
      view.setNeedsDisplay(true);
    }
  }
}
