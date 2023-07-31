class PlaceHold {
  static int checkStyle(int style) {
    style |= SWT.NO_FOCUS;
    return style & (~(SWT.H_SCROLL | SWT.V_SCROLL));
  }
}
