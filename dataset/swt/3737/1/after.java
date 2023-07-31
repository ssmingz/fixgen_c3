class PlaceHold {
  public void test_getLineStyle() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getLineStyle();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
