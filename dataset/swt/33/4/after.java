class PlaceHold {
  int widgetExtStyle() {
    int bits = super.widgetExtStyle() & (~OS.WS_EX_MDICHILD);
    if (!OS.IsWinCE) {
      if (parent == null) {
        if ((style & SWT.ON_TOP) != 0) {
          bits |= OS.WS_EX_TOOLWINDOW;
        }
      }
    }
    if (parent != null) {
      if (OS.IsWin95) {
        return bits;
      }
      if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
        return bits;
      }
    }
    if ((style & SWT.ON_TOP) != 0) {
      bits |= OS.WS_EX_TOPMOST;
    }
    return bits;
  }
}
