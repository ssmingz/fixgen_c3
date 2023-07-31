class PlaceHold {
  public void test_hashCode() {
    Font font = new Font(display, SwtJunit.testFontName, 40, SWT.BOLD | SWT.ITALIC);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      font.hashCode();
    }
    stopMeasuring();
    font.dispose();
    commitMeasurements();
    assertPerformance();
  }
}
