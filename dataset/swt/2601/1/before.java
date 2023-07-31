class PlaceHold {
  public void setHorizontalPixel(int pixel) {
    checkWidget();
    if (getCharCount() == 0) {
      return;
    }
    if (pixel < 0) {
      pixel = 0;
    }
    int clientAreaWidth = getClientArea().width;
    if (clientAreaWidth > 0) {
      int width = lineCache.getWidth();
      if (pixel > (width - clientAreaWidth)) {
        pixel = Math.max(0, width - clientAreaWidth);
      }
    }
    scrollHorizontalBar(pixel - horizontalScrollOffset);
  }
}
