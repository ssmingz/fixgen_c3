class PlaceHold {
  public void test_getHeaderHeight() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getHeaderHeight(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Tree)");
      }
      return;
    }
    assertEquals(0, tree.getHeaderHeight());
    tree.setHeaderVisible(true);
    assertTrue(tree.getHeaderHeight() > 0);
    tree.setHeaderVisible(false);
    assertEquals(0, tree.getHeaderHeight());
  }
}
