class PlaceHold {
  public void test_ConstructorLjava_lang_StringII() {
    FontData fd = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    String fdString = fd.toString();
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    }
    stopMeasuring();
    commitMeasurements();
    assertPerformance();
  }
}
