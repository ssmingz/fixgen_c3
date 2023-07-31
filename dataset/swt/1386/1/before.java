class PlaceHold {
  public void test_setTextLjava_lang_String() {
    assertTrue(":a:", treeColumn.getText() == "");
    treeColumn.setText("text");
    assertTrue(":b:", treeColumn.getText() == "text");
    try {
      treeColumn.setText(null);
      fail("No exception thrown for column header == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
