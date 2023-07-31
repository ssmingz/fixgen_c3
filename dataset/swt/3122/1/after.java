class PlaceHold {
  public void insert(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (hooks(Verify) || filters(Verify)) {
      Point selection = getSelection();
      string = verifyText(string, selection.x, selection.y, null);
      if (string == null) {
        return;
      }
    }
    if ((style & SWT.SINGLE) != 0) {
      insertEditText(string);
    } else {
      NSTextView widget = ((NSTextView) (view));
      NSRange range = widget.selectedRange();
      NSString str;
      if (textLimit != LIMIT) {
        str = getInsertString(string, range);
      } else {
        str = NSString.stringWith(string);
      }
      widget.textStorage().replaceCharactersInRange(range, str);
    }
    if (string.length() != 0) {
      sendEvent(Modify);
    }
  }
}
