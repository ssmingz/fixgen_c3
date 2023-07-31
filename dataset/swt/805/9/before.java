class PlaceHold {
  public void test_setNameLjava_lang_String() {
    FontData data = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    String name = "name";
    startMeasuring();
    for (int i = 0; i < COUNT; i++) {
      data.setName(name);
    }
    stopMeasuring();
    commitMeasurements();
    assertPerformance();
  }
}
