class PlaceHold {
  public void test_getRed() {
    Color color = new Color(display, 128, 64, 255);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      color.getRed();
    }
    stopMeasuring();
    color.dispose();
    commitMeasurements();
    assertPerformance();
  }
}
