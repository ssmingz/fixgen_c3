class PlaceHold {
  public void test_getFontData() {
    Font font = new Font(display, SwtJunit.testFontName, 40, SWT.BOLD | SWT.ITALIC);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      font.getFontData();
    }
    stopMeasuring();
    font.dispose();
    commitMeasurements();
    assertPerformance();
  }
}
