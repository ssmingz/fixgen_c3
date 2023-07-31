class PlaceHold {
  public void test_getCaretLineNumber() {
    text.setBounds(0, 0, 500, 500);
    assertTrue(":a:", text.getCaretLineNumber() == 0);
    text.setText("Line0\r\n");
    assertTrue(":b:", text.getCaretLineNumber() == 0);
    text.setTopIndex(1);
    assertTrue(":c:", text.getCaretLineNumber() == 0);
    text.append("Line1");
    assertTrue(":d:", text.getCaretLineNumber() == 1);
    String newText = "Line-1\r\n";
    text.setSelection(0, 0);
    text.insert(newText);
    assertTrue(":e:", text.getCaretLineNumber() == 1);
    text.setSelection(0, 0);
    assertTrue(":f:", text.getCaretLineNumber() == 0);
    text.setSelection(8, 8);
    assertTrue(":g:", text.getCaretLineNumber() == 1);
  }
}
