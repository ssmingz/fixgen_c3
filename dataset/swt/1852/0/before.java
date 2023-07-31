class PlaceHold {
  public void
      test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_ImageData() {
    final int COUNT = 7000;
    String name = getPath((imageFilenames[0] + ".") + imageFormats[0]);
    FileInputStream inStream = null;
    try {
      inStream = new FileInputStream(name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    ImageData[] imageData = new ImageLoader().load(inStream);
    Image[] images = new Image[COUNT];
    try {
      inStream.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      images[i] = new Image(display, imageData[0]);
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      images[i].dispose();
    }
    disposeMeter(meter);
  }
}
