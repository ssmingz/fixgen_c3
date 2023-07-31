class PlaceHold {
  public void test_setLocaleLjava_lang_String() {
    FontData data = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    String localeString = Locale.ENGLISH.toString();
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      data.setLocale(localeString);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
