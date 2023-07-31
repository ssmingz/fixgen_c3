class PlaceHold {
  public void test_getBackground() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getBackground();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
