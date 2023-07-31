class PlaceHold {
  static int checkStyle(int style) {
    style |= SWT.NO_FOCUS;
    IsVertical = (style & SWT.V_SCROLL) != 0;
    return style & (~(SWT.H_SCROLL | SWT.V_SCROLL));
  }
}
