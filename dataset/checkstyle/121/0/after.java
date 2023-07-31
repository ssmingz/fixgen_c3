class PlaceHold {
  @Test
  public void testDecideRange() {
    final IntFilter filter = new CSVFilter("0-2");
    assertFalse("less than", filter.accept(-1));
    assertTrue("equal 0", filter.accept(0));
    assertTrue("equal 1", filter.accept(1));
    assertTrue("equal 2", filter.accept(2));
    assertFalse("greater than", filter.accept(3));
  }
}
