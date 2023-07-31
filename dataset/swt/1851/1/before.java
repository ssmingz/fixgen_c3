class PlaceHold {
  public void
      test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_Rectangle() {
    Image[] images = new Image[COUNT];
    Rectangle rectangle = new Rectangle(0, 0, 100, 100);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      images[i] = new Image(display, rectangle);
    }
    stopMeasuring();
    for (int i = 0; i < COUNT; i++) {
      images[i].dispose();
    }
    commitMeasurements();
    assertPerformance();
  }
}
