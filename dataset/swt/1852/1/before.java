class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceII() {
    final int COUNT = 7000;
    Image[] images = new Image[COUNT];
    PerformanceMeter meter = createMeter("Image constr.(Device,II)");
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
