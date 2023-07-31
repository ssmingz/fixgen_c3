class PlaceHold {
  public void test_paste() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded test_paste(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text).");
      }
      return;
    }
    text.setText("01234567890");
    text.setSelection(2, 4);
    assertEquals("01234567890", text.getText());
    text.copy();
    text.setSelection(0);
    text.paste();
    assertEquals("2301234567890", text.getText());
    text.copy();
    text.setSelection(3);
    text.paste();
    assertEquals("230231234567890", text.getText());
    text.setText(("0" + delimiterString) + "1");
    text.selectAll();
    text.copy();
    text.setSelection(0);
    text.paste();
    assertEquals((((("0" + delimiterString) + "1") + "0") + delimiterString) + "1", text.getText());
    makeCleanEnvironment(true);
    text.setText("01234567890");
    text.setSelection(2, 4);
    assertEquals("01234567890", text.getText());
    text.copy();
    text.setSelection(0);
    text.paste();
    assertEquals("2301234567890", text.getText());
    text.copy();
    text.setSelection(3);
    text.paste();
    assertEquals("230231234567890", text.getText());
    makeCleanEnvironment(true);
    text.setText(("0" + delimiterString) + "1");
    text.selectAll();
    text.copy();
    text.setSelection(0);
    text.paste();
    if (SwtTestUtil.fCheckSWTPolicy) {
      assertEquals(
          (((("0" + delimiterString) + "1") + "0") + delimiterString) + "1", text.getText());
    }
  }
}
