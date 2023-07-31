class PlaceHold {
  public void test_getForeground() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getForeground();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
