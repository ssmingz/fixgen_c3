class PlaceHold {
  public void test_intersectionLorg_eclipse_swt_graphics_Rectangle() {
    Rectangle r1 = new Rectangle(1, 2, 3, 4);
    Rectangle r2 = new Rectangle(3, 3, 2, 2);
    assertEquals(
        "Rectangle intersection incorrect", new Rectangle(3, 3, 1, 2), r1.intersection(r2));
    r2 = new Rectangle(3, 3, 0, 0);
    assertEquals(
        "Rectangle intersection incorrect", new Rectangle(3, 3, 0, 0), r1.intersection(r2));
    r2 = new Rectangle(3, 3, -1, -1);
    assertEquals(
        "Rectangle intersection incorrect", new Rectangle(0, 0, 0, 0), r1.intersection(r2));
    try {
      r1.intersection(null);
      fail("No exception thrown for rectangle == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
