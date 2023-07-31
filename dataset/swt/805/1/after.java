class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceIII() {
    Color[] colors = new Color[COUNT];
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      colors[i] = new Color(display, 102, 255, 3);
    }
    meter.stop();
    for (int i = 0; i < COUNT; i++) {
      colors[i].dispose();
    }
    disposeMeter(meter);
  }
}
