class PlaceHold {
  public void test_setSelectionEmpty() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_setSelectionEmpty(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_TabFolder)");
      }
      return;
    }
    int number = 10;
    for (int i = 0; i < number; i++) {
      new TabItem(tabFolder, 0);
    }
    for (int i = 0; i < number; i++) {
      tabFolder.setSelection(i);
      assertEquals(i, tabFolder.getSelectionIndex());
    }
    makeCleanEnvironment();
    tabFolder.setSelection(-1);
    assertEquals(0, tabFolder.getSelection().length);
  }
}
