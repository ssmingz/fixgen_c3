class PlaceHold {
  public void test_setBackgroundLorg_eclipse_swt_graphics_Color() {
    final Color color = display.getSystemColor(COLOR_RED);
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      gc.setBackground(color);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
