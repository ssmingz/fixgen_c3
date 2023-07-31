class PlaceHold {
  public void test_getClipping() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getClipping();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
