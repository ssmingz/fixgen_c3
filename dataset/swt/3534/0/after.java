class PlaceHold {
  public void test_copyAreaIIIIII() {
    gc.setBackground(display.getSystemColor(COLOR_WHITE));
    gc.setForeground(display.getSystemColor(COLOR_WHITE));
    gc.fillRectangle(image.getBounds());
    gc.setForeground(display.getSystemColor(COLOR_BLUE));
    gc.drawLine(5, 5, 10, 10);
    gc.drawLine(5, 10, 10, 5);
    Rectangle imageBounds = image.getBounds();
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      int pos = i % imageBounds.width;
      gc.copyArea(pos, pos, 2, 2, pos + 2, pos);
    }
    meter.stop();
    disposeMeter(meter);
  }
}
