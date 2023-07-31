class PlaceHold {
  public void test_copyAreaLorg_eclipse_swt_graphics_ImageII() {
    Color white = display.getSystemColor(COLOR_WHITE);
    Color blue = display.getSystemColor(COLOR_BLUE);
    RGB whiteRGB = getRealRGB(white);
    RGB blueRGB = getRealRGB(blue);
    gc.setBackground(white);
    gc.setForeground(white);
    gc.fillRectangle(image.getBounds());
    gc.setForeground(blue);
    gc.drawLine(5, 0, 10, 0);
    final Image image = new Image(display, 12, 12);
    gc.copyArea(image, 0, 0);
    ImageData imageData = image.getImageData();
    PaletteData palette = imageData.palette;
    int pixel = imageData.getPixel(4, 0);
    assertEquals(":a:", whiteRGB, palette.getRGB(pixel));
    pixel = imageData.getPixel(5, 0);
    assertEquals(":b:", blueRGB, palette.getRGB(pixel));
    pixel = imageData.getPixel(10, 0);
    assertEquals(":c:", blueRGB, palette.getRGB(pixel));
    pixel = imageData.getPixel(11, 0);
    assertEquals(":d:", whiteRGB, palette.getRGB(pixel));
    testPerformance(
        new Runnable() {
          public void run() {
            gc.copyArea(image, 0, 0);
          }
        });
    image.dispose();
  }
}
