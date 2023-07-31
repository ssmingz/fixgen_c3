class PlaceHold {
  public void test_copy() {
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
