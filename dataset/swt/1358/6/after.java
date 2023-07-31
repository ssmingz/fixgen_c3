class PlaceHold {
  public void test_cut() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_cut(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_custom_CCombo).");
      }
      return;
    }
    ccombo.setText("123456");
    ccombo.setSelection(new Point(1, 3));
    ccombo.cut();
    assertTrue(":a:", ccombo.getText().equals("1456"));
  }
}
