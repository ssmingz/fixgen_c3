class PlaceHold {
  public void test_setForegroundLorg_eclipse_swt_graphics_Color() {
    Color color = new Color(shell.getDisplay(), 255, 0, 0);
    gc.setForeground(color);
    assertEquals(color, gc.getForeground());
    try {
      gc.setForeground(null);
      fail("No exception thrown for null color");
    } catch (IllegalArgumentException e) {
    }
    assertEquals(gc.getForeground(), gc.getForeground());
    color.dispose();
    try {
      gc.setForeground(color);
      fail("No exception thrown for color disposed");
    } catch (IllegalArgumentException e) {
    }
  }
}
