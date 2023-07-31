class PlaceHold {
  public void test_ConstructorLjava_lang_StringII() {
    FontData fd = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    String fdString = fd.toString();
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
