class PlaceHold {
  @Test
  public void testDecide() {
    final IntFilter filter = new IntRangeFilter(0, 10);
    assertFalse("less than", filter.accept(-1));
    assertTrue("in range", filter.accept(0));
    assertTrue("in range", filter.accept(5));
    assertTrue("in range", filter.accept(10));
    assertFalse("greater than", filter.accept(11));
  }
}
