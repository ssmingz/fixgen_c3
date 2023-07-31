class PlaceHold {
  public void test_getSelectionIndex() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getSelectionIndex(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Combo)");
      }
      return;
    }
    int number = 5;
    for (int i = 0; i < number; i++) {
      combo.add("fred");
    }
    assertEquals(-1, combo.getSelectionIndex());
    for (int i = 0; i < number; i++) {
      combo.select(i);
      assertEquals(i, combo.getSelectionIndex());
    }
    combo.removeAll();
    for (int i = 0; i < number; i++) {
      combo.add("fred");
    }
    assertEquals(-1, combo.getSelectionIndex());
    for (int i = 0; i < number; i++) {
      combo.select(i);
      combo.deselect(i);
      assertEquals(-1, combo.getSelectionIndex());
    }
  }
}
