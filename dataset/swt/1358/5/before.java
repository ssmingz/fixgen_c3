class PlaceHold {
  public void test_copy() {
    combo.setText("123456");
    combo.setSelection(new Point(1, 3));
    combo.copy();
    combo.setSelection(new Point(0, 0));
    combo.paste();
    assertTrue(":a:", combo.getText().equals("23123456"));
  }
}
