class PlaceHold {
  public void test_getBackground() {
    final Image image = new Image(display, 10, 10);
    testPerformance(
        new Runnable() {
          public void run() {
            image.getBackground();
          }
        });
    image.dispose();
    try {
      image.getBackground();
      fail("No exception thrown for disposed image");
    } catch (SWTException e) {
      assertEquals("Incorrect exception thrown for disposed image", ERROR_GRAPHIC_DISPOSED, e);
    }
  }
}
