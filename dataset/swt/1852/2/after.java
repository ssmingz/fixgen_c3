class PlaceHold {
  public void
      test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_Rectangle() {
    final int COUNT = 60000;
    Rectangle rectangle = new Rectangle(0, 0, 100, 100);
    PerformanceMeter meter = createMeter("Image constr.(Device,Rectangle)");
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      new Image(display, rectangle).dispose();
    }
    meter.stop();
    disposeMeter(meter);
  }
}
