class PlaceHold {
  public void test_intersectLorg_eclipse_swt_graphics_Rectangle() {
    Rectangle r1 = new Rectangle(1, 2, 3, 4);
    Rectangle r2 = new Rectangle(3, 3, 2, 2);
    r1.intersect(r2);
    assertEquals("Rectangle intersect incorrect", new Rectangle(3, 3, 1, 2), r1);
    r1 = new Rectangle(1, 2, 3, 4);
    r2 = new Rectangle(3, 3, 0, 0);
    r1.intersect(r2);
    assertEquals("Rectangle intersect incorrect", new Rectangle(3, 3, 0, 0), r1);
    r1 = new Rectangle(1, 2, 3, 4);
    r2 = new Rectangle(3, 3, -1, -1);
    r1.intersect(r2);
    assertEquals("Rectangle intersect incorrect", new Rectangle(0, 0, 0, 0), r1);
    try {
      r1.intersect(null);
      fail("No exception thrown for rectangle == null");
    } catch (IllegalArgumentException e) {
      assertSWTProblem("Incorrect exception thrown for rectangle == null", ERROR_NULL_ARGUMENT, e);
    }
  }
}
