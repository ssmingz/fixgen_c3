class PlaceHold {
  void checkComposited() {
    if ((state & CANVAS) != 0) {
      if ((style & SWT.TRANSPARENT) != 0) {
        int hwndParent = parent.handle;
        int bits = OS.GetWindowLong(hwndParent, GWL_EXSTYLE);
        bits |= OS.WS_EX_COMPOSITED;
        OS.SetWindowLong(hwndParent, GWL_EXSTYLE, bits);
      }
    }
  }
}
