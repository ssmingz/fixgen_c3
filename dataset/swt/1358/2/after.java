class PlaceHold {
  public void test_paste() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_paste(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_custom_CCombo).");
      }
      return;
    }
    ccombo.setText("123456");
    ccombo.setSelection(new Point(1, 3));
    ccombo.cut();
    assertTrue(":a:", ccombo.getText().equals("1456"));
    ccombo.paste();
    assertTrue(":a:", ccombo.getText().equals("123456"));
  }
}
