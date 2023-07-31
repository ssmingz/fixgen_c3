class PlaceHold {
  public void test_getBlue() {
    final Color color = new Color(display, 0, 0, 255);
    try {
      assertEquals("color.getBlue()", color.getBlue(), 255);
      testPerformance(
          new Runnable() {
            public void run() {
              color.getBlue();
            }
          });
    } finally {
      color.dispose();
    }
  }
}
