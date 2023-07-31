class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_RGB() {
    Color[] colors = new Color[COUNT];
    RGB rgb = new RGB(102, 255, 3);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      colors[i] = new Color(display, rgb);
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      colors[i].dispose();
    }
    disposeMeter(meter);
  }
}
