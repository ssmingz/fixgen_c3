class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_widgets_TreeII() {
    try {
      new TreeItem(tree, 0, 5);
      fail("No exception thrown for illegal index argument");
    } catch (IllegalArgumentException e) {
    }
  }
}
