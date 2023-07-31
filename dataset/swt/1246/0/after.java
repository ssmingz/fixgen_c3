class PlaceHold {
  int getHorizontalIncrement() {
    GC gc = getGC();
    int increment = gc.getFontMetrics().getAverageCharWidth();
    gc.dispose();
    return increment;
  }
}
