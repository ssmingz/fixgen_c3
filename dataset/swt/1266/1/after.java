class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceII() {
    final int COUNT = 60000;
    PerformanceMeter meter = createMeter("Image constr.(Device,II)");
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      new Image(display, 100, 100).dispose();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
