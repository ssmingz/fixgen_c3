class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceII() {
    Image[] images = new Image[COUNT];
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      images[i] = new Image(display, 100, 100);
    }
    stopMeasuring();
    for (int i = 0; i < COUNT; i++) {
      images[i].dispose();
    }
    commitMeasurements();
    assertPerformance();
  }
}
