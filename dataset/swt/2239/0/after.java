class PlaceHold {
  public void test_getBoundsI() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getBoundsI(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_TreeItem)");
      }
      return;
    }
    test_getBoundsIA();
    test_getBoundsIB();
    test_getBoundsIC();
    test_getBoundsID();
  }
}
