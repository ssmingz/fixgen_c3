class PlaceHold {
  public void test_copy() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_copy(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_custom_CCombo).");
      }
      return;
    }
    ccombo.setText("123456");
    ccombo.setSelection(new Point(1, 3));
    ccombo.copy();
    ccombo.setSelection(new Point(0, 0));
    ccombo.paste();
    assertTrue(":a:", ccombo.getText().equals("23123456"));
  }
}
