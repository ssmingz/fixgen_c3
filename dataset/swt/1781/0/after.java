class PlaceHold {
  void calculateScrollBars() {
    ScrollBar horizontalBar = getHorizontalBar();
    ScrollBar verticalBar = getVerticalBar();
    setScrollBars(true);
    if (verticalBar != null) {
      verticalBar.setIncrement(getVerticalIncrement());
    }
    if (horizontalBar != null) {
      horizontalBar.setIncrement(getHorizontalIncrement());
    }
  }
}
