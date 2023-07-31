class PlaceHold {
  public void test_getGreen() {
    final Color color = new Color(display, 0, 255, 0);
    try {
      assertEquals("color.getGreen()", color.getGreen(), 255);
      testPerformance(
          new Runnable() {
            public void run() {
              color.getGreen();
            }
          });
    } finally {
      color.dispose();
    }
  }
}
