class PlaceHold {
  public void test_hashCode() {
    FontData data = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      data.hashCode();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
