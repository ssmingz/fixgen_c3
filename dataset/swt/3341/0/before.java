class PlaceHold {
  public void test_textExtentLjava_lang_String() {
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.textExtent("test \t\nstring");
    }
    meter.stop();
    disposeMeter(meter);
  }
}
