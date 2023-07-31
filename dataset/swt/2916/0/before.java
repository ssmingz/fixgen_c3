class PlaceHold {
  public void test_setBackgroundLorg_eclipse_swt_graphics_Color() {
    Color color = new Color(shell.getDisplay(), 255, 0, 0);
    gc.setBackground(color);
    assertEquals(color, gc.getBackground());
    try {
      gc.setBackground(null);
      fail("No exception thrown for null color");
    } catch (IllegalArgumentException e) {
    }
    assertEquals(gc.getBackground(), gc.getBackground());
    color.dispose();
    try {
      gc.setBackground(color);
      fail("No exception thrown for color disposed");
    } catch (IllegalArgumentException e) {
    }
  }
}
