class PlaceHold {
  static int checkStyle(int style) {
    style |= SWT.NO_FOCUS;
    return checkBits(style, HORIZONTAL, VERTICAL, 0, 0, 0, 0);
  }
}
