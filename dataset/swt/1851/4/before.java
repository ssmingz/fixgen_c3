class PlaceHold {
  public void test_loadLjava_lang_String() {
    if (isJ2ME()) {
      return;
    }
    ImageLoader loader = new ImageLoader();
    String fileName = getPath((imageFilenames[0] + ".") + imageFormats[0]);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      loader.load(fileName);
    }
    stopMeasuring();
    commitMeasurements();
    assertPerformance();
  }
}
