class PlaceHold {
  public void test_getDescent() {
    final FontMetrics fm = gc.getFontMetrics();
    fm.getDescent();
    testPerformance(
        new Runnable() {
          public void run() {
            fm.getDescent();
          }
        });
  }
}
