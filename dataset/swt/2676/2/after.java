class PlaceHold {
  int widgetStyle() {
    int bits = super.widgetStyle() | OS.WS_TABSTOP;
    if ((style & SWT.CALENDAR) != 0) {
      return bits | OS.MCS_NOTODAY;
    }
    bits &= ~OS.WS_CLIPCHILDREN;
    if ((style & SWT.TIME) != 0) {
      bits |= OS.DTS_TIMEFORMAT;
    }
    if ((style & SWT.DATE) != 0) {
      bits |= ((style & SWT.MEDIUM) != 0) ? OS.DTS_SHORTDATECENTURYFORMAT : OS.DTS_LONGDATEFORMAT;
      if ((style & SWT.DROP_DOWN) == 0) {
        bits |= OS.DTS_UPDOWN;
      }
    }
    return bits;
  }
}
