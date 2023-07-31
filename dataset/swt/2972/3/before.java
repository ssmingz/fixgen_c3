class PlaceHold {
  public void test_getHeaderHeight() {
    assertEquals(0, table.getHeaderHeight());
    table.setHeaderVisible(true);
    assertTrue(table.getHeaderHeight() > 0);
    table.setHeaderVisible(false);
    assertEquals(0, table.getHeaderHeight());
  }
}
