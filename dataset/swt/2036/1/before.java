class PlaceHold {
  int widgetStyle() {
    int bits = super.widgetStyle();
    if ((style & SWT.SMOOTH) != 0) {
      bits |= OS.PBS_SMOOTH;
    }
    if ((style & SWT.VERTICAL) != 0) {
      bits |= OS.PBS_VERTICAL;
    }
    return bits;
  }
}
