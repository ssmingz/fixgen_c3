class PlaceHold {
  public void test_paste() {
    Clipboard clipboard = new Clipboard(text.getDisplay());
    TextTransfer transfer = TextTransfer.getInstance();
    String convertedText;
    clipboard.setContents(new String[] {"x"}, new Transfer[] {transfer});
    text.copy();
    text.paste();
    assertTrue(":a:", text.getCharCount() == 1);
    text.setSelectionRange(0, 0);
    text.copy();
    text.paste();
    assertTrue(":b:", text.getCharCount() == 2);
    text.setText("0123456789");
    text.setSelectionRange(0, 1);
    text.copy();
    text.setCaretOffset(0);
    text.paste();
    assertTrue(":c:", text.getText().equals("00123456789"));
    text.setSelectionRange(1, 2);
    text.copy();
    text.setText("");
    text.paste();
    assertTrue(":d:", text.getText().equals("01"));
    text.setText("");
    clipboard.setContents(
        new String[] {"\rLine1\nLine2\r\nLine3\n\rLine4\n"}, new Transfer[] {transfer});
    text.paste();
    if (SwtTestUtil.isWindows) {
      convertedText = "\r\nLine1\r\nLine2\r\nLine3\r\n\r\nLine4\r\n";
    } else {
      convertedText = "\nLine1\nLine2\nLine3\n\nLine4\n";
    }
    assertTrue(":f:", (text.getText() != null) && text.getText().equals(convertedText));
    text.setText("");
    clipboard.setContents(new String[] {"Line1\r\nLine2"}, new Transfer[] {transfer});
    text.paste();
    if (SwtTestUtil.isWindows) {
      convertedText = "Line1\r\nLine2";
    } else {
      convertedText = "Line1\nLine2";
    }
    assertTrue(":g:", (text.getText() != null) && text.getText().equals(convertedText));
    text.setText("");
    clipboard.setContents(new String[] {"Line1\rLine2"}, new Transfer[] {transfer});
    text.paste();
    if (SwtTestUtil.isWindows) {
      convertedText = "Line1\r\nLine2";
    } else {
      convertedText = "Line1\nLine2";
    }
    assertTrue(":h:", (text.getText() != null) && text.getText().equals(convertedText));
    text.setText("");
    clipboard.setContents(new String[] {"Line1\nLine2"}, new Transfer[] {transfer});
    text.paste();
    if (SwtTestUtil.isWindows) {
      convertedText = "Line1\r\nLine2";
    } else {
      convertedText = "Line1\nLine2";
    }
    assertTrue(":i:", (text.getText() != null) && text.getText().equals(convertedText));
    text.setText("");
    clipboard.dispose();
  }
}
