class PlaceHold {
  int widgetStyle() {
    int bits = super.widgetStyle();
    if ((style & SWT.PASSWORD) != 0) {
      bits |= OS.ES_PASSWORD;
    }
    if ((style & SWT.CENTER) != 0) {
      bits |= OS.ES_CENTER;
    }
    if ((style & SWT.RIGHT) != 0) {
      bits |= OS.ES_RIGHT;
    }
    if ((style & SWT.READ_ONLY) != 0) {
      bits |= OS.ES_READONLY;
    }
    if ((style & SWT.SINGLE) != 0) {
      return bits | OS.ES_AUTOHSCROLL;
    }
    bits |= (OS.ES_MULTILINE | OS.ES_AUTOHSCROLL) | OS.ES_NOHIDESEL;
    if ((style & SWT.WRAP) != 0) {
      bits &= ~(OS.WS_HSCROLL | OS.ES_AUTOHSCROLL);
    }
    return bits;
  }
}
