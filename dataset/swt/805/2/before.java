class PlaceHold {
  public void test_setLocaleLjava_lang_String() {
    FontData data = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    String localeString = Locale.ENGLISH.toString();
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      data.setLocale(localeString);
    }
    stopMeasuring();
    commitMeasurements();
    assertPerformance();
  }
}
