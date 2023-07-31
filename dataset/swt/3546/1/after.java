class PlaceHold {
  public void test_getCharWidthC() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.getCharWidth('a');
    }
    meter.stop();
    disposeMeter(meter);
  }
}
