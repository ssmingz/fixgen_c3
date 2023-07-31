class PlaceHold {
  public void
      test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_Rectangle() {
    Image[] images = new Image[COUNT];
    Rectangle rectangle = new Rectangle(0, 0, 100, 100);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      images[i] = new Image(display, rectangle);
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      images[i].dispose();
    }
    disposeMeter(meter);
  }
}
