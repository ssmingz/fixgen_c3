class PlaceHold {
  public void test_getTransparencyMask() {
    String name = getPath(transparentImageFilenames[0]);
    FileInputStream inStream = null;
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
      imageData.getTransparencyMask();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
