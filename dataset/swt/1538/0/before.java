class PlaceHold {
  private int textWidth(GC gc) {
    String text = getText();
    int textWidth = 0;
    if (text != null) {
      textWidth = gc.stringExtent(text).x;
    }
    if (textWidth == 0) {
      textWidth = DEFAULT_TEXT_WIDTH;
    }
    return textWidth;
  }
}
