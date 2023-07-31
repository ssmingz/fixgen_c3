class PlaceHold {
  static int checkStyle(int style) {
    style |= SWT.NO_FOCUS;
    if ((style & SWT.SEPARATOR) != 0) {
      style = checkBits(style, VERTICAL, HORIZONTAL, 0, 0, 0, 0);
      return checkBits(style, SHADOW_OUT, SHADOW_IN, SHADOW_NONE, 0, 0, 0);
    }
    return checkBits(style, LEFT, CENTER, RIGHT, 0, 0, 0);
  }
}
