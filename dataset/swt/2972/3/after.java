class PlaceHold {
  public void test_getHeaderHeight() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getHeaderHeight(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Table))");
      }
      return;
    }
    assertEquals(0, table.getHeaderHeight());
    table.setHeaderVisible(true);
    assertTrue(table.getHeaderHeight() > 0);
    table.setHeaderVisible(false);
    assertEquals(0, table.getHeaderHeight());
  }
}
