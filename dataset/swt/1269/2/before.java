class PlaceHold {
  public void test_copy() {
    Clipboard clipboard = new Clipboard(text.getDisplay());
    TextTransfer transfer = TextTransfer.getInstance();
    String clipboardText;
    String convertedText;
    String before = ((String) (clipboard.getContents(transfer)));
    text.setSelectionRange(0, 0);
    text.copy();
    clipboardText = ((String) (clipboard.getContents(transfer)));
    assertTrue(":a:", before == null ? clipboardText == null : before.equals(clipboardText));
    before = ((String) (clipboard.getContents(transfer)));
    text.setText("0123456789");
    text.setSelectionRange(0, 0);
    text.copy();
    clipboardText = ((String) (clipboard.getContents(transfer)));
    assertTrue(":c:", before == null ? clipboardText == null : before.equals(clipboardText));
    text.setSelectionRange(0, 1);
    text.copy();
    clipboardText = ((String) (clipboard.getContents(transfer)));
    assertTrue(":d:", (clipboardText != null) && clipboardText.equals("0"));
    text.setSelectionRange(1, 2);
    text.copy();
    clipboardText = ((String) (clipboard.getContents(transfer)));
    assertTrue(":e:", (clipboardText != null) && clipboardText.equals("12"));
    text.setText("\rLine1\nLine2\r\nLine3\n\rLine4\n");
    text.setSelectionRange(0, text.getCharCount());
    text.copy();
    clipboardText = ((String) (clipboard.getContents(transfer)));
    if (SwtTestUtil.isWindows) {
      convertedText = "\r\nLine1\r\nLine2\r\nLine3\r\n\r\nLine4\r\n";
    } else {
      convertedText = "\nLine1\nLine2\nLine3\n\nLine4\n";
    }
    assertTrue(":f:", (clipboardText != null) && clipboardText.equals(convertedText));
    text.setText("Line1\r\nLine2");
    text.setSelectionRange(0, text.getCharCount());
    text.copy();
    clipboardText = ((String) (clipboard.getContents(transfer)));
    if (SwtTestUtil.isWindows) {
      convertedText = "Line1\r\nLine2";
    } else {
      convertedText = "Line1\nLine2";
    }
    assertTrue(":g:", (clipboardText != null) && clipboardText.equals(convertedText));
    testRtfCopy();
    clipboard.dispose();
  }
}
