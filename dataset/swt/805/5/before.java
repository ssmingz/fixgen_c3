class PlaceHold {
  public void test_Constructor() {
    Region[] regions = new Region[COUNT];
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      regions[i] = new Region();
    }
    stopMeasuring();
    for (int i = 0; i < COUNT; i++) {
      regions[i].dispose();
    }
    commitMeasurements();
    assertPerformance();
  }
}
