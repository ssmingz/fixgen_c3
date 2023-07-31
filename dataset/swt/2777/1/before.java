class PlaceHold {
  public void test_cut() {
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
