class PlaceHold {
  public void test_hashCode() {
    Cursor cursor = new Cursor(display, SWT.CURSOR_WAIT);
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      cursor.hashCode();
    }
    stopMeasuring();
    cursor.dispose();
    commitMeasurements();
    assertPerformance();
  }
}
