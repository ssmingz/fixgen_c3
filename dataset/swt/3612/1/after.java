class PlaceHold {
  static int checkStyle(int style) {
    if ((style & SWT.WRAP) != 0) {
      style &= ~SWT.WRAP;
    }
    return style & (~(SWT.H_SCROLL | SWT.V_SCROLL));
  }
}
