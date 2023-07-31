class PlaceHold {
  public void test_copy() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_copy(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text).");
      }
      return;
    }
    text.copy();
    text.selectAll();
    text.copy();
    assertEquals("", text.getSelectionText());
    text.setText("00000");
    text.selectAll();
    text.copy();
    text.setSelection(2);
    assertEquals("", text.getSelectionText());
    text.setText("");
    text.paste();
    assertEquals("00000", text.getText());
    makeCleanEnvironment(true);
    text.copy();
    text.selectAll();
    text.copy();
    assertEquals("", text.getSelectionText());
    text.setText("00000");
    text.selectAll();
    text.copy();
    text.setSelection(2);
    assertEquals("", text.getSelectionText());
    text.setText("");
    text.paste();
    assertEquals("00000", text.getText());
  }
}
