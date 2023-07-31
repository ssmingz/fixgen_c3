class PlaceHold {
  public void test_selectAll() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_selectAll(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text).");
      }
      return;
    }
    text.setText("01234567890");
    assertEquals("01234567890", text.getText());
    text.selectAll();
    assertEquals("01234567890", text.getSelectionText());
    text.cut();
    assertEquals("", text.getText());
    text.setText(("01234" + delimiterString) + "567890");
    assertEquals(("01234" + delimiterString) + "567890", text.getText());
    text.selectAll();
    assertEquals(("01234" + delimiterString) + "567890", text.getSelectionText());
    text.cut();
    assertEquals("", text.getText());
    makeCleanEnvironment(true);
    text.setText("01234567890");
    assertEquals("01234567890", text.getText());
    text.selectAll();
    assertEquals("01234567890", text.getSelectionText());
    text.cut();
    assertEquals("", text.getText());
    if (SwtTestUtil.fCheckBogusTestCases) {
      text.setText(("01234" + delimiterString) + "567890");
      assertEquals(("01234" + delimiterString) + "567890", text.getText());
      text.selectAll();
      assertEquals(("01234" + delimiterString) + "567890", text.getSelectionText());
      text.cut();
      assertEquals("", text.getText());
    }
  }
}
