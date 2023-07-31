class PlaceHold {
  public void test_setAlphaIII() {
    final int COUNT = 80000000;
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      imageData.setAlpha(0, 0, 0xaa);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
