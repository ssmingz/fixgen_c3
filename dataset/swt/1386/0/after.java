class PlaceHold {
  public void test_setTextLjava_lang_String() {
    assertEquals(":a:", tableColumn.getText(), "");
    tableColumn.setText("text");
    assertEquals(":b:", tableColumn.getText(), "text");
    try {
      tableColumn.setText(null);
      fail("No exception thrown for column header == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
