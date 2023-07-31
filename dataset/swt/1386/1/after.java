class PlaceHold {
  public void test_setTextLjava_lang_String() {
    assertEquals(":a:", treeColumn.getText(), "");
    treeColumn.setText("text");
    assertEquals(":b:", treeColumn.getText(), "text");
    try {
      treeColumn.setText(null);
      fail("No exception thrown for column header == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
