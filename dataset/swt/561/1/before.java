class PlaceHold {
  public void test_selectAll() {
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
