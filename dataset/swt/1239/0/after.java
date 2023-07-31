class PlaceHold {
  int widgetExtStyle() {
    int bits = super.widgetExtStyle() & (~OS.WS_EX_MDICHILD);
    if (OS.IsWin95) {
      return bits;
    }
    if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) < ((4 << 16) | 10)) {
      return bits;
    }
    if ((style & SWT.ON_TOP) != 0) {
      bits |= OS.WS_EX_TOPMOST;
    }
    return bits;
  }
}
