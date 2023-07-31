class PlaceHold {
  public void test_getBlue() {
    Color color = new Color(display, 0, 0, 255);
    try {
      assertEquals("color.getBlue()", color.getBlue(), 255);
    } finally {
      color.dispose();
    }
  }
}
