class PlaceHold {
  public void test_getBounds() {
    Region region = new Region(display);
    region.add(new Rectangle(10, 10, 20, 20));
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      region.getBounds();
    }
    meter.stop();
    region.dispose();
    disposeMeter(meter);
  }
}
