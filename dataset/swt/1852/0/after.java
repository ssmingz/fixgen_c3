class PlaceHold {
  public void
      test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_ImageData() {
    final int COUNT = 60000;
    String name = getPath((imageFilenames[0] + ".") + imageFormats[0]);
    FileInputStream inStream = null;
    try {
      inStream = new FileInputStream(name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    ImageData[] imageData = new ImageLoader().load(inStream);
    try {
      inStream.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      new Image(display, imageData[0]).dispose();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
