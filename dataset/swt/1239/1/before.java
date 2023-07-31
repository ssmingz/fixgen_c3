class PlaceHold {
  int widgetExtStyle() {
    int bits = 0;
    if ((style & SWT.NO_TRIM) != 0) {
      return bits;
    }
    if (OS.IsPPC) {
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
