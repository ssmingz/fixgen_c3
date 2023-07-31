class PlaceHold {
  private int textHeight(GC gc) {
    String text = getText();
    if (text == null) {
      return 0;
    } else {
      return gc.stringExtent(text).y;
    }
  }
}
