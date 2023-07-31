class PlaceHold {
  public Point getSelection() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      if (selectionRange == null) {
        NSString str = ((NSTextField) (view)).stringValue();
        return new Point(((int) (str.length())), ((int) (str.length())));
      }
      return new Point(
          ((int) (selectionRange.location)),
          ((int) (selectionRange.location + selectionRange.length)));
    } else {
      NSTextView widget = ((NSTextView) (view));
      NSRange range = widget.selectedRange();
      return new Point(((int) (range.location)), ((int) (range.location + range.length)));
    }
  }
}
