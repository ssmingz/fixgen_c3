class PlaceHold {
  public void test_getHeaderHeight() {
    assertEquals(0, tree.getHeaderHeight());
    tree.setHeaderVisible(true);
    assertTrue(tree.getHeaderHeight() > 0);
    tree.setHeaderVisible(false);
    assertEquals(0, tree.getHeaderHeight());
  }
}
