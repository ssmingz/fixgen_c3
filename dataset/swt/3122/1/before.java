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
      NSString str = NSString.stringWith(string);
      NSTextView widget = ((NSTextView) (view));
      NSRange range = widget.selectedRange();
      widget.textStorage().replaceCharactersInRange(range, str);
    }
    if (string.length() != 0) {
      sendEvent(Modify);
    }
  }
}
