class PlaceHold {
  public void test_getAscent() {
    final FontMetrics fm = gc.getFontMetrics();
    fm.getAscent();
    testPerformance(
        new Runnable() {
          public void run() {
            fm.getAscent();
          }
        });
  }
}
