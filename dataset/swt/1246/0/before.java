class PlaceHold {
  int getHorizontalIncrement() {
    GC gc = new GC(this);
    int increment = gc.getFontMetrics().getAverageCharWidth();
    gc.dispose();
    return increment;
  }
}
