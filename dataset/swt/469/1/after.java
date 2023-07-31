class PlaceHold {
  static int checkStyle(int style) {
    style = checkBits(style, LEFT, CENTER, RIGHT, 0, 0, 0);
    if ((style & SWT.SINGLE) != 0) {
      style &= ~((SWT.H_SCROLL | SWT.V_SCROLL) | SWT.WRAP);
    }
    if ((style & SWT.WRAP) != 0) {
      style |= SWT.MULTI;
      style &= ~SWT.H_SCROLL;
    }
    if ((style & SWT.MULTI) != 0) {
      style &= ~SWT.PASSWORD;
    }
    if ((style & (SWT.SINGLE | SWT.MULTI)) != 0) {
      return style;
    }
    if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) != 0) {
      return style | SWT.MULTI;
    }
    return style | SWT.SINGLE;
  }
}
