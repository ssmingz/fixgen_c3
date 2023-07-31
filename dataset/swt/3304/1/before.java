class PlaceHold {
  void claimRightFreeSpace() {
    int newHorizontalOffset = Math.max(0, contentWidth - getClientArea().width);
    if (newHorizontalOffset < horizontalScrollOffset) {
      scrollHorizontalBar(newHorizontalOffset - horizontalScrollOffset);
    }
  }
}
