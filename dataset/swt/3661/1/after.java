class PlaceHold {
  public void test_getXORMode() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getXORMode();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
