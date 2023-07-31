class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLjava_lang_String() {
    int numFileNames = imageFilenames.length;
    int numFormats = imageFormats.length;
    for (int k = 0; k < numFileNames; k++) {
      String fileName = imageFilenames[k];
      for (int i = 0; i < numFormats; i++) {
        String format = imageFormats[i];
        String pathName = getPath((fileName + ".") + format);
        Image[] images = new Image[COUNT];
        PerformanceMeter meter = createMeter((fileName + ".") + format);
        meter.start();
        for (int j = 0; j < COUNT; j++) {
          images[j] = new Image(display, pathName);
        }
        meter.stop();
        for (int j = 0; j < COUNT; j++) {
          images[j].dispose();
        }
        disposeMeter(meter);
      }
    }
  }
}
