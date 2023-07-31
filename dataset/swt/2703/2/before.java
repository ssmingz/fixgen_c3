class PlaceHold {
  public void setHorizontalIndex(int offset) {
    checkWidget();
    int clientAreaWidth = getClientArea().width;
    if (getCharCount() == 0) {
      return;
    }
    if (offset < 0) {
      offset = 0;
    }
    offset *= getHorizontalIncrement();
    if (clientAreaWidth > 0) {
      int width = contentWidth.getWidth();
      if (offset > (width - clientAreaWidth)) {
        offset = Math.max(0, width - clientAreaWidth);
      }
    }
    scrollHorizontalBar(offset - horizontalScrollOffset);
  }
}
