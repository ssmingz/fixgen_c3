class PlaceHold {
  static int checkStyle(int style) {
    if (OS.INIT_CAIRO) {
      style &= ~SWT.NO_BACKGROUND;
    }
    style &= ~SWT.TRANSPARENT;
    return style;
  }
}
