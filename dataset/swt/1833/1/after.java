class PlaceHold {
  public void test_getBounds() {
    Rectangle bounds = new Rectangle(0, 0, 10, 20);
    Image image = new Image(display, bounds.width, bounds.height);
    image.dispose();
    try {
      image.getBounds();
      fail("No exception thrown for disposed image");
    } catch (SWTException e) {
      assertSWTProblem("Incorrect exception thrown for disposed image", ERROR_GRAPHIC_DISPOSED, e);
    }
    image = new Image(display, bounds.width, bounds.height);
    Rectangle bounds1 = image.getBounds();
    image.dispose();
    assertEquals(":a:", bounds, bounds1);
    image = new Image(display, bounds);
    bounds1 = image.getBounds();
    image.dispose();
    assertEquals(":b:", bounds, bounds1);
    ImageData imageData =
        new ImageData(
            bounds.width, bounds.height, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
    image = new Image(display, imageData);
    bounds1 = image.getBounds();
    image.dispose();
    assertEquals(":c:", bounds, bounds1);
  }
}
