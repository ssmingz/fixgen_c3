class PlaceHold {
  public void test_copy() {
    ccombo.setText("123456");
    ccombo.setSelection(new Point(1, 3));
    ccombo.copy();
    ccombo.setSelection(new Point(0, 0));
    ccombo.paste();
    assertTrue(":a:", ccombo.getText().equals("23123456"));
  }
}
