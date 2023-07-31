class PlaceHold {
  public void test_subtractLorg_eclipse_swt_graphics_Region() {
    Region region = new Region(display);
    region.add(new Rectangle(0, 0, COUNT * 2, COUNT * 2));
    Region[] regions = new Region[COUNT];
    for (int i = 0; i < COUNT; i++) {
      Region newRegion = new Region(display);
      newRegion.add(new Rectangle(i, i, i + 1, i + 1));
      regions[i] = newRegion;
    }
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      region.subtract(regions[i]);
    }
    meter.stop();
    region.dispose();
    for (int i = 0; i < COUNT; i++) {
      regions[i].dispose();
    }
    disposeMeter(meter);
  }
}
