class PlaceHold {
  public void test_hashCode() {
    ImageData imageData = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
    Image image = new Image(display, imageData);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      image.hashCode();
    }
    stopMeasuring();
    image.dispose();
    commitMeasurements();
    assertPerformance();
  }
}
