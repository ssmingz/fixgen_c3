class PlaceHold {
  int widgetExtStyle() {
    int bits = 0;
    if (OS.IsWinCE) {
      if ((style & SWT.CLOSE) != 0) {
        bits |= OS.WS_EX_CAPTIONOKBTN;
      }
    }
    if ((style & SWT.TOOL) != 0) {
      bits |= OS.WS_EX_TOOLWINDOW;
    }
    if ((style & SWT.RESIZE) != 0) {
      return bits;
    }
    if ((style & SWT.BORDER) != 0) {
      bits |= OS.WS_EX_DLGMODALFRAME;
    }
    return bits;
  }
}
