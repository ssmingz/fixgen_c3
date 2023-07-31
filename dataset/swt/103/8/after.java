class PlaceHold {
  public void test_containsLorg_eclipse_swt_graphics_Point() {
    Rectangle r = new Rectangle(1, 2, 3, 4);
    assertTrue("Rectangle should contain Point(1, 2)", r.contains(new Point(1, 2)));
    assertTrue("Rectangle should contain Point(3, 4)", r.contains(new Point(3, 4)));
    assertTrue("Rectangle should contain point (3, 5)", r.contains(new Point(3, 5)));
    assertTrue("Rectangle should not contain Point(9, 10)", !r.contains(new Point(9, 10)));
    assertTrue("Rectangle should not contain point (-1, -1)", !r.contains(new Point(-1, -1)));
    try {
      r.contains(null);
      fail("No exception thrown for rectangle == null");
    } catch (IllegalArgumentException e) {
      assertSWTProblem("Incorrect exception thrown for rectangle == null", ERROR_NULL_ARGUMENT, e);
    }
  }
}
