class PlaceHold {
  public void test_getLineWidth() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getLineWidth();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
