class PlaceHold {
  public void test_intersectsLorg_eclipse_swt_graphics_Rectangle() {
    Rectangle r1 = new Rectangle(1, 2, 3, 4);
    Rectangle r2 = new Rectangle(2, 3, 7, 8);
    assertTrue("Rectangle(1, 2, 3, 4) should intersect Rectangle(2, 3, 7, 8)", r1.intersects(r2));
    assertTrue("Rectangle(2, 3, 7, 8) should intersect Rectangle(1, 2, 3, 4)", r2.intersects(r1));
    r2 = new Rectangle(200, 300, 400, 500);
    assertTrue(
        "Rectangle(1, 2, 3, 4) should not intersect Rectangle(200, 300, 400, 500)",
        !r1.intersects(r2));
    assertTrue(
        "Rectangle(200, 300, 400, 500) should not intersect Rectangle(1, 2, 3, 4)",
        !r2.intersects(r1));
    r2 = new Rectangle(3, 3, 0, 0);
    assertTrue("Rectangle(1, 2, 3, 4) should intersect Rectangle(3, 3, 0, 0)", r1.intersects(r2));
    assertTrue("Rectangle(3, 3, 0, 0) should intersect Rectangle(1, 2, 3, 4)", r2.intersects(r1));
    r2 = new Rectangle(3, 3, -1, -1);
    assertTrue(
        "Rectangle(1, 2, 3, 4) should not intersect Rectangle(3, 3, -1, -1)", !r1.intersects(r2));
    assertTrue(
        "Rectangle(3, 3, -1, -1) should not intersect Rectangle(1, 2, 3, 4)", !r2.intersects(r1));
    try {
      r1.intersects(null);
      fail("No exception thrown for rectangle == null");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect exception thrown for rectangle == null", ERROR_NULL_ARGUMENT, e);
    }
  }
}
