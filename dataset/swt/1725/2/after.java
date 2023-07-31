class PlaceHold {
  static int checkStyle(int style) {
    if ((style & SWT.SINGLE) != 0) {
      style &= ~(SWT.H_SCROLL | SWT.V_SCROLL);
    } else {
      style |= SWT.MULTI;
    }
    return style;
  }
}
