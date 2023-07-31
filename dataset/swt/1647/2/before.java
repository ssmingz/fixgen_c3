class PlaceHold {
  public void test_getRed() {
    Color color = new Color(display, 255, 0, 0);
    try {
      assertEquals("color.getRed()", color.getRed(), 255);
    } finally {
      color.dispose();
    }
  }
}
