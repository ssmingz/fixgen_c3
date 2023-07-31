class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceII() {
    Image image;
    try {
      image = new Image(display, -1, 10);
      image.dispose();
      fail("No exception thrown for width <= 0");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect exception thrown for width <= 0", ERROR_INVALID_ARGUMENT, e);
    }
    try {
      image = new Image(display, 0, 10);
      image.dispose();
      fail("No exception thrown for width <= 0");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect exception thrown for width <= 0", ERROR_INVALID_ARGUMENT, e);
    }
    try {
      image = new Image(display, 10, -20);
      image.dispose();
      fail("No exception thrown for height <= 0");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect exception thrown for height <= 0", ERROR_INVALID_ARGUMENT, e);
    }
    try {
      image = new Image(display, 10, 0);
      image.dispose();
      fail("No exception thrown for height <= 0");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect exception thrown for height <= 0", ERROR_INVALID_ARGUMENT, e);
    }
    image = new Image(null, 10, 10);
    image.dispose();
    image = new Image(display, 10, 10);
    image.dispose();
    testPerformance(
        new Runnable() {
          public void run() {
            new Image(display, 10, 10).dispose();
          }
        });
  }
}
