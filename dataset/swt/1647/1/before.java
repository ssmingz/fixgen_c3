class PlaceHold {
  public void test_getGreen() {
    Color color = new Color(display, 0, 255, 0);
    try {
      assertEquals("color.getGreen()", color.getGreen(), 255);
    } finally {
      color.dispose();
    }
  }
}
