class PlaceHold {
  @Test
  public void testDecideEmptyRange() {
    final IntFilter filter = new CSVFilter("2-0");
    assertFalse("less than", filter.accept(-1));
    assertFalse("equal 0", filter.accept(0));
    assertFalse("equal 1", filter.accept(1));
    assertFalse("equal 2", filter.accept(2));
    assertFalse("greater than", filter.accept(3));
  }
}
