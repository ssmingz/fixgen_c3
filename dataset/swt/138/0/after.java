class PlaceHold {
  public void test_ConstructorLjava_lang_String() {
    if (isJ2ME()) {
      return;
    }
    int numFormats = imageFormats.length;
    int numFileNames = imageFilenames.length;
    for (int k = 0; k < numFileNames; k++) {
      String fileName = imageFilenames[k];
      for (int i = 0; i < numFormats; i++) {
        String format = imageFormats[i];
        String fullName = getPath((fileName + ".") + format);
        PerformanceMeter meter = createMeter((fileName + ".") + format);
        meter.start();
        for (int j = 0; j < COUNT; j++) {
          new ImageData(fullName);
        }
        meter.stop();
        disposeMeter(meter);
      }
    }
  }
}
