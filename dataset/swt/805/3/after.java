class PlaceHold {
  public void test_getRed() {
    Color color = new Color(display, 128, 64, 255);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      color.getRed();
    }
    meter.stop();
    color.dispose();
    disposeMeter(meter);
  }
}
