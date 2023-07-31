class PlaceHold {
  static int checkStyle(int style) {
    if (OS.USE_CAIRO) {
      style |= SWT.SMOOTH;
    }
    return checkBits(style, HORIZONTAL, VERTICAL, 0, 0, 0, 0);
  }
}
