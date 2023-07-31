class PlaceHold {
  boolean showLocation(int x, int line) {
    int clientAreaWidth = getClientArea().width;
    int verticalIncrement = getVerticalIncrement();
    int horizontalIncrement = clientAreaWidth / 4;
    boolean scrolled = false;
    if (x < 0) {
      x = Math.max(horizontalScrollOffset * (-1), x - horizontalIncrement);
      scrollHorizontalBar(x);
      scrolled = true;
    } else if (x > clientAreaWidth) {
      x = Math.min(lineCache.getWidth() - horizontalScrollOffset, x + horizontalIncrement);
      scrollHorizontalBar(x - clientAreaWidth);
      scrolled = true;
    }
    if (line < topIndex) {
      setVerticalScrollOffset(line * verticalIncrement, true);
      scrolled = true;
    } else if (line > getBottomIndex()) {
      setVerticalScrollOffset(
          ((line - getBottomIndex()) * verticalIncrement) + verticalScrollOffset, true);
      scrolled = true;
    }
    return scrolled;
  }
}
