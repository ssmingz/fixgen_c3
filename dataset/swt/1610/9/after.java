class PlaceHold {
  static int checkStyle(int style) {
    style = checkBits(style, PUSH, ARROW, CHECK, RADIO, TOGGLE, 0);
    if ((style & (SWT.PUSH | SWT.TOGGLE)) != 0) {
      return checkBits(style, CENTER, LEFT, RIGHT, 0, 0, 0);
    }
    if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
      return checkBits(style, LEFT, RIGHT, CENTER, 0, 0, 0);
    }
    if ((style & SWT.ARROW) != 0) {
      style |= SWT.NO_FOCUS;
      return checkBits(style, UP, DOWN, LEFT, RIGHT, 0, 0);
    }
    return style;
  }
}
