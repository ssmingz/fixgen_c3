class PlaceHold {
  void claimRightFreeSpace() {
    int newHorizontalOffset =
        Math.max(0, renderer.getWidth() - ((getClientArea().width - leftMargin) - rightMargin));
    if (newHorizontalOffset < horizontalScrollOffset) {
      scrollHorizontal(newHorizontalOffset - horizontalScrollOffset, true);
    }
  }
}
