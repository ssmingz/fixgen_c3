class PlaceHold {
  public void test_clone() {
    String name = getPath((imageFilenames[0] + ".") + imageFormats[0]);
    FileInputStream inStream = null;
    try {
      inStream = new FileInputStream(name);
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    ImageLoader loader = new ImageLoader();
    ImageData data = loader.load(inStream)[0];
    try {
      inStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      data.clone();
    }
    stopMeasuring();
    commitMeasurements();
    assertPerformance();
  }
}
