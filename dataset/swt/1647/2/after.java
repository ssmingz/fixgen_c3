class PlaceHold {
  public void test_getRed() {
    final Color color = new Color(display, 255, 0, 0);
    try {
      assertEquals("color.getRed()", color.getRed(), 255);
      testPerformance(
          new Runnable() {
            public void run() {
              color.getRed();
            }
          });
    } finally {
      color.dispose();
    }
  }
}
