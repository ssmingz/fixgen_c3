class PlaceHold {
  int widgetStyle() {
    int bits = super.widgetStyle() | OS.WS_CLIPCHILDREN;
    if ((style & SWT.NO_FOCUS) != 0) {
      bits |= OS.TCS_FOCUSNEVER;
    }
    return (bits | OS.TCS_TABS) | OS.TCS_TOOLTIPS;
  }
}
