class PlaceHold {
  static int checkStyle(int style) {
    if ((style & SWT.SINGLE) != 0) {
      style &= ~(((SWT.H_SCROLL | SWT.V_SCROLL) | SWT.WRAP) | SWT.MULTI);
    } else {
      style |= SWT.MULTI;
      if ((style & SWT.WRAP) != 0) {
        style &= ~SWT.H_SCROLL;
      }
    }
    return style;
  }
}
