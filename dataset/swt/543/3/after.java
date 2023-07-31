class PlaceHold {
  public void test_setCursorLocationLorg_eclipse_swt_graphics_Point() {
    Display display = new Display();
    try {
      display.setCursorLocation(new Point(0, 0));
      try {
        display.setCursorLocation(null);
        fail("No exception thrown for null argument");
      } catch (IllegalArgumentException e) {
        assertEquals(
            "Incorrect exception thrown for setCursorLocation with null argument",
            ERROR_NULL_ARGUMENT,
            e);
      }
    } finally {
      display.dispose();
    }
  }
}
