class PlaceHold {
  public void test_getRGBs() {
    assertNull(":a:", imageData.getRGBs());
    RGB[] rgbs = new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255)};
    imageData = new ImageData(IMAGE_DIMENSION, IMAGE_DIMENSION, 8, new PaletteData(rgbs));
    assertArrayEquals(":b:", rgbs, imageData.getRGBs());
  }
}
