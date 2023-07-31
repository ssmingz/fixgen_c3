class PlaceHold {
  void claimRightFreeSpace() {
    int newHorizontalOffset = Math.max(0, lineCache.getWidth() - getClientArea().width);
    if (newHorizontalOffset < horizontalScrollOffset) {
      scrollHorizontalBar(newHorizontalOffset - horizontalScrollOffset);
    }
  }
}
