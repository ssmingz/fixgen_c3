class PlaceHold {
  public void test_Constructor() {
    final int COUNT = 9000;
    Region[] regions = new Region[COUNT];
    PerformanceMeter meter = createMeter("Region constr.()");
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
