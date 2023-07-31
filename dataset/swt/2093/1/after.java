class PlaceHold {
  public void test_getAdvanceWidthC() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getAdvanceWidth('a');
    }
    meter.stop();
    disposeMeter(meter);
  }
}
