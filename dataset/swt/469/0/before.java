class PlaceHold {
  static int checkStyle(int style) {
    style = checkBits(style, TOP, BOTTOM, 0, 0, 0, 0);
    if (OS.IsPPC) {
      style |= SWT.BOTTOM;
    }
    return style & (~(SWT.H_SCROLL | SWT.V_SCROLL));
  }
}
