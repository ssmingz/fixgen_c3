class PlaceHold {
  public int getCaretPosition() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return 0;
    } else {
      NSRange range = ((NSTextView) (view)).selectedRange();
      return range.location;
    }
  }
}
