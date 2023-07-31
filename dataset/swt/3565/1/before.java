class PlaceHold {
  void scrollClipViewToPoint(int id, int sel, int clipView, NSPoint point) {
    if (shouldScroll) {
      super.scrollClipViewToPoint(id, sel, clipView, point);
    }
  }
}
