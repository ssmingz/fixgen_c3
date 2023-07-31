class PlaceHold {
  int widgetExtStyle() {
    int bits = 0;
    if (!OS.IsPPC) {
      if ((style & SWT.BORDER) != 0) {
        bits |= OS.WS_EX_CLIENTEDGE;
      }
    }
    if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
      return bits;
    }
    bits |= OS.WS_EX_NOINHERITLAYOUT;
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      bits |= OS.WS_EX_LAYOUTRTL;
    }
    return bits;
  }
}
