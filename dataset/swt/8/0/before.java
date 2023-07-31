class PlaceHold {
  public void test_getRGBs() {
    RGB[] rgbs = new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255)};
    PaletteData data = new PaletteData(rgbs);
    assertEquals(":a:", rgbs, data.getRGBs());
    data = new PaletteData(0xff0000, 0xff00, 0xff);
    assertNull(":b:", data.getRGBs());
  }
}
