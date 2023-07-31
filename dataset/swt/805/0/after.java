class PlaceHold {
  public void test_hashCode() {
    Font font = new Font(display, SwtJunit.testFontName, 40, SWT.BOLD | SWT.ITALIC);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      font.hashCode();
    }
    meter.stop();
    font.dispose();
    disposeMeter(meter);
  }
}
