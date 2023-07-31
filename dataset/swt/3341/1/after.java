class PlaceHold {
  public void test_stringExtentLjava_lang_String() {
    final int COUNT = 1500000;
    final String STRING = "test \t\nstring";
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.stringExtent(STRING);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
