class PlaceHold {
  public void test_Constructor() {
    Region[] regions = new Region[COUNT];
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      regions[i] = new Region();
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      regions[i].dispose();
    }
    disposeMeter(meter);
  }
}
