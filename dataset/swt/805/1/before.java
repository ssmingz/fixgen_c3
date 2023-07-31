class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceIII() {
    Color[] colors = new Color[COUNT];
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      colors[i] = new Color(display, 102, 255, 3);
    }
    stopMeasuring();
    for (int i = 0; i < COUNT; i++) {
      colors[i].dispose();
    }
    commitMeasurements();
    assertPerformance();
  }
}
