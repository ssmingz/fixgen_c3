class PlaceHold {
  public void test_paste() {
    ccombo.setText("123456");
    ccombo.setSelection(new Point(1, 3));
    ccombo.cut();
    assertTrue(":a:", ccombo.getText().equals("1456"));
    ccombo.paste();
    assertTrue(":a:", ccombo.getText().equals("123456"));
  }
}
