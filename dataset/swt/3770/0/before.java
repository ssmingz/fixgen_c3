class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      NSString str = new NSCell(((NSTextField) (view)).cell()).title();
      int length = ((int) (str.length()));
      int selStart = Math.min(Math.max(Math.min(start, end), 0), length);
      int selEnd = Math.min(Math.max(Math.max(start, end), 0), length);
      selectionRange = new NSRange();
      selectionRange.location = selStart;
      selectionRange.length = selEnd - selStart;
      NSText fieldEditor = ((NSControl) (view)).currentEditor();
      if (fieldEditor != null) {
        fieldEditor.setSelectedRange(selectionRange);
        fieldEditor.scrollRangeToVisible(selectionRange);
      }
    } else {
      int length = ((int) (((NSTextView) (view)).textStorage().length()));
      int selStart = Math.min(Math.max(Math.min(start, end), 0), length);
      int selEnd = Math.min(Math.max(Math.max(start, end), 0), length);
      NSRange range = new NSRange();
      range.location = selStart;
      range.length = selEnd - selStart;
      NSTextView widget = ((NSTextView) (view));
      widget.setSelectedRange(range);
      widget.scrollRangeToVisible(range);
    }
  }
}
