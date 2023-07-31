class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_widgets_TreeItemII() {
    try {
      new TreeItem(treeItem, 0, 5);
      fail("No exception thrown for illegal index argument");
    } catch (IllegalArgumentException e) {
    }
    assertEquals(1, tree.getItemCount());
  }
}
