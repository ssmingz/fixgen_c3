class PlaceHold {
  public void test_copy() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_copy(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Combo).");
      }
      return;
    }
    combo.setText("123456");
    combo.setSelection(new Point(1, 3));
    combo.copy();
    combo.setSelection(new Point(0, 0));
    combo.paste();
    assertTrue(":a:", combo.getText().equals("23123456"));
  }
}
