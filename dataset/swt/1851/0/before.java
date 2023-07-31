class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_FontData() {
    FontData data = new FontData(SwtJunit.testFontName, 10, SWT.BOLD | SWT.ITALIC);
    Font[] fonts = new Font[COUNT];
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      fonts[i] = new Font(display, data);
    }
    stopMeasuring();
    for (int i = 0; i < COUNT; i++) {
      fonts[i].dispose();
    }
    commitMeasurements();
    assertPerformance();
  }
}
