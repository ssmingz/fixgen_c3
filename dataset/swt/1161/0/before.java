class PlaceHold {
  void claimRightFreeSpace() {
    int newHorizontalOffset =
        Math.max(0, lineCache.getWidth() - ((getClientArea().width - leftMargin) - rightMargin));
    if (newHorizontalOffset < horizontalScrollOffset) {
      scrollHorizontalBar(newHorizontalOffset - horizontalScrollOffset);
    }
  }
}
