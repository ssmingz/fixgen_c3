class PlaceHold {
  void scrollHorizontalBar(int pixels) {
    if (pixels == 0) {
      return;
    }
    ScrollBar horizontalBar = getHorizontalBar();
    if (horizontalBar != null) {
      horizontalBar.setSelection(horizontalScrollOffset + pixels);
    }
    scrollHorizontal(pixels);
  }
}
