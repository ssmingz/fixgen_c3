class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_Device() {
    final int COUNT = 9000;
    Region[] regions = new Region[COUNT];
    PerformanceMeter meter = createMeter("Region constr.(Device)");
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      regions[i] = new Region(display);
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      regions[i].dispose();
    }
    disposeMeter(meter);
  }
}
