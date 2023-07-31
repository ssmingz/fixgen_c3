class PlaceHold {
  void insertEditText(String string) {
    ignoreVerify = true;
    int length = string.length();
    Point selection = getSelection();
    if (hasFocus()) {
      if (textLimit != LIMIT) {
        int charCount = getCharCount();
        if (((charCount - (selection.y - selection.x)) + length) > textLimit) {
          length = (textLimit - charCount) + (selection.y - selection.x);
        }
      }
      char[] buffer = new char[length];
      string.getChars(0, buffer.length, buffer, 0);
      NSString nsstring = NSString.stringWithCharacters(buffer, buffer.length);
      NSText editor = ((NSTextField) (view)).currentEditor();
      editor.replaceCharactersInRange(editor.selectedRange(), nsstring);
      selectionRange = null;
    } else {
      String oldText = getText();
      if (textLimit != LIMIT) {
        int charCount = oldText.length();
        if (((charCount - (selection.y - selection.x)) + length) > textLimit) {
          string = string.substring(0, (textLimit - charCount) + (selection.y - selection.x));
        }
      }
      String newText =
          (oldText.substring(0, selection.x) + string) + oldText.substring(selection.y);
      NSString nsstring = NSString.stringWith(newText);
      new NSCell(((NSTextField) (view)).cell()).setTitle(nsstring);
      selectionRange = null;
      setSelection(new Point(selection.x + string.length(), 0));
    }
    ignoreVerify = false;
  }
}
