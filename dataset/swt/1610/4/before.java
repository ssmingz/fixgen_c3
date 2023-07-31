class PlaceHold {
  static int checkStyle(int style) {
    return style & (~(SWT.H_SCROLL | SWT.V_SCROLL));
  }
}
