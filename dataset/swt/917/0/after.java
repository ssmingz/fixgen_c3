class PlaceHold {
  public void test_hashCode() {
    final FontMetrics fm1 = gc.getFontMetrics();
    FontMetrics fm2 = gc.getFontMetrics();
    if (fm1.equals(fm2)) {
      assertEquals(fm1.hashCode(), fm2.hashCode());
    }
    testPerformance(
        new Runnable() {
          public void run() {
            fm1.hashCode();
          }
        });
  }
}
