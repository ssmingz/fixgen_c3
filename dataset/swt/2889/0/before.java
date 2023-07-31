class PlaceHold {
  public void test_stringExtentLjava_lang_String() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.stringExtent("test \t\nstring");
    }
    meter.stop();
    disposeMeter(meter);
  }
}
