class PlaceHold {
  public void test_getLeading() {
    final FontMetrics fm = gc.getFontMetrics();
    fm.getLeading();
    testPerformance(
        new Runnable() {
          public void run() {
            fm.getLeading();
          }
        });
  }
}
