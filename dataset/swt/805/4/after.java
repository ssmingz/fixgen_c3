class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceII() {
    Image[] images = new Image[COUNT];
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      images[i] = new Image(display, 100, 100);
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      images[i].dispose();
    }
    disposeMeter(meter);
  }
}
