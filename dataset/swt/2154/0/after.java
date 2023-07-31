class PlaceHold {
  public void test_Constructor() {
    final int COUNT = 9000;
    PerformanceMeter meter = createMeter("Region constr.()");
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      new Region().dispose();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
