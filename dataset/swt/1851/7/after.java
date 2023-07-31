class PlaceHold {
  public void test_getBackground() {
    Image image = new Image(display, 100, 100);
    image.setBackground(display.getSystemColor(COLOR_GREEN));
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      image.getBackground();
    }
    meter.stop();
    image.dispose();
    disposeMeter(meter);
  }
}
