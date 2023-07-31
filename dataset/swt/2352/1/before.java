class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceIII() {
    Color color = new Color(display, 0, 0, 0);
    color.dispose();
    color = new Color(display, 255, 255, 255);
    color.dispose();
    color = new Color(display, 20, 20, 20);
    color.dispose();
    color = new Color(display, 102, 255, 0);
    color.dispose();
    color = new Color(null, 0, 0, 0);
    color.dispose();
    try {
      color = new Color(display, -10, -10, -10);
      color.dispose();
      fail("No exception thrown for rgb < 0");
    } catch (IllegalArgumentException e) {
    }
    try {
      color = new Color(display, 1000, 2000, 3000);
      color.dispose();
      fail("No exception thrown for rgb > 255");
    } catch (IllegalArgumentException e) {
    }
    try {
      color = new Color(display, 10, 10, 256);
      color.dispose();
      fail("No exception thrown for blue > 255");
    } catch (IllegalArgumentException e) {
    }
  }
}
