class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_RGB() {
    Color[] colors = new Color[COUNT];
    RGB rgb = new RGB(102, 255, 3);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      colors[i] = new Color(display, rgb);
    }
    stopMeasuring();
    for (int i = 0; i < COUNT; i++) {
      colors[i].dispose();
    }
    commitMeasurements();
    assertPerformance();
  }
}
