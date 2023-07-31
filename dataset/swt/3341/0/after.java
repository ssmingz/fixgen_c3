class PlaceHold {
  public void test_textExtentLjava_lang_String() {
    final int COUNT = 200000;
    final String STRING = "test \t\nstring";
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.textExtent(STRING);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
