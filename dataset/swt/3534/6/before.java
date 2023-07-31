class PlaceHold {
  public void test_scaledToII() {
    int imageDimension = 8;
    RGB[] rgbs = new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255)};
    byte[] pixelData = new byte[(imageDimension * imageDimension) / 8];
    pixelData[0] = 0x4f;
    imageData =
        new ImageData(imageDimension, imageDimension, 1, new PaletteData(rgbs), 1, pixelData);
    int newHeight = imageDimension * 10;
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      imageData.scaledTo(imageDimension, newHeight);
    }
    stopMeasuring();
    commitMeasurements();
    assertPerformance();
  }
}
