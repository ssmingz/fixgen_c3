class PlaceHold {
  public void test_getFont() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getFont();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
