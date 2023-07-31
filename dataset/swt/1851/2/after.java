class PlaceHold {
  public void test_getImageData() {
    ImageData imageData = new ImageData(10, 10, 1, new PaletteData(new RGB[] {new RGB(0, 0, 0)}));
    Image image = new Image(display, imageData);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      image.getImageData();
    }
    meter.stop();
    image.dispose();
    disposeMeter(meter);
  }
}
