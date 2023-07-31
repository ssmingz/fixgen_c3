class PlaceHold {
  public void test_cut() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_cut(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text).");
      }
      return;
    }
    text.cut();
    text.setText("01234567890");
    text.setSelection(2, 5);
    text.cut();
    assertEquals("01567890", text.getText());
    text.selectAll();
    text.cut();
    assertEquals("", text.getText());
    makeCleanEnvironment(true);
    text.cut();
    text.setText("01234567890");
    text.setSelection(2, 5);
    text.cut();
    assertEquals("01567890", text.getText());
    text.selectAll();
    text.cut();
    assertEquals("", text.getText());
  }
}
