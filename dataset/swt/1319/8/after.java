class PlaceHold {
  public void test_getAverageCharWidth() {
    final FontMetrics fm = gc.getFontMetrics();
    fm.getAverageCharWidth();
    testPerformance(
        new Runnable() {
          public void run() {
            fm.getAverageCharWidth();
          }
        });
  }
}
