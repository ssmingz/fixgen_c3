class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_Device() {
    final int COUNT = 9000;
    PerformanceMeter meter = createMeter("Region constr.(Device)");
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      new Region(display).dispose();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
