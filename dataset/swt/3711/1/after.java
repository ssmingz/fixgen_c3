class PlaceHold {
  boolean scrollHorizontalBar(int pixels) {
    if (pixels == 0) {
      return false;
    }
    ScrollBar horizontalBar = getHorizontalBar();
    if (horizontalBar != null) {
      horizontalBar.setSelection(horizontalScrollOffset + pixels);
    }
    scrollHorizontal(pixels);
    return true;
  }
}
