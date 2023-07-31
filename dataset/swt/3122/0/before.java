class PlaceHold {
  public void append(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (hooks(Verify) || filters(Verify)) {
      int charCount = getCharCount();
      string = verifyText(string, charCount, charCount, null);
      if (string == null) {
        return;
      }
    }
    NSString str = NSString.stringWith(string);
    if ((style & SWT.SINGLE) != 0) {
      setSelection(getCharCount());
      insertEditText(string);
    } else {
      NSTextView widget = ((NSTextView) (view));
      NSTextStorage storage = widget.textStorage();
      NSRange range = new NSRange();
      range.location = storage.length();
      storage.replaceCharactersInRange(range, str);
      range.location = storage.length();
      widget.scrollRangeToVisible(range);
      widget.setSelectedRange(range);
    }
    if (string.length() != 0) {
      sendEvent(Modify);
    }
  }
}
