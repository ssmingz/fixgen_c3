class PlaceHold {
  int widgetStyle() {
    int bits = super.widgetStyle() | OS.WS_TABSTOP;
    if ((style & SWT.CALENDAR) != 0) {
      return bits;
    }
    bits &= ~OS.WS_CLIPCHILDREN;
    if ((style & SWT.TIME) != 0) {
      bits |= OS.DTS_TIMEFORMAT;
    }
    if ((style & SWT.DATE) != 0) {
      bits |= OS.DTS_SHORTDATECENTURYFORMAT | OS.DTS_UPDOWN;
    }
    return bits;
  }
}
