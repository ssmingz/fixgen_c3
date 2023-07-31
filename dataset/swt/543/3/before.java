class PlaceHold {
  public void test_setCursorLocationLorg_eclipse_swt_graphics_Point() {
    Display display = new Display();
    try {
      display.setCursorLocation(new Point(0, 0));
      try {
        display.setCursorLocation(null);
        fail("No exception thrown for null argument");
      } catch (IllegalArgumentException e) {
      }
    } finally {
      display.dispose();
    }
  }
}
