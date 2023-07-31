class PlaceHold {
  public void test_getTransparencyType() {
    FileInputStream inStream = null;
    String name = getPath((imageFilenames[0] + '.') + imageFormats[imageFormats.length - 1]);
    try {
      inStream = new FileInputStream(name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Image image = new Image(Display.getDefault(), inStream);
    imageData = image.getImageData();
    image.dispose();
    try {
      inStream.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      imageData.getTransparencyType();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
