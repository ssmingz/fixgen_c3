class PlaceHold {
  public void test_hashCode() {
    Cursor cursor = new Cursor(display, SWT.CURSOR_WAIT);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      cursor.hashCode();
    }
    meter.stop();
    cursor.dispose();
    disposeMeter(meter);
  }
}
