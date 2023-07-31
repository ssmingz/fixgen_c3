class PlaceHold {
  public void test_getHeight() {
    final FontMetrics fm = gc.getFontMetrics();
    fm.getHeight();
    testPerformance(
        new Runnable() {
          public void run() {
            fm.getHeight();
          }
        });
  }
}
