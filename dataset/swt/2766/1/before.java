class PlaceHold {
  static int checkStyle(int style) {
    style &= ~SWT.TRANSPARENT;
    return style;
  }
}
