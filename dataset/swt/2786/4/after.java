class PlaceHold {
  void updateOrientation() {
    int bits = OS.GetWindowLong(handle, GWL_EXSTYLE);
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      bits |= OS.WS_EX_LAYOUTRTL;
    } else {
      bits &= ~OS.WS_EX_LAYOUTRTL;
    }
    bits &= ~OS.WS_EX_RTLREADING;
    OS.SetWindowLong(handle, GWL_EXSTYLE, bits);
    OS.InvalidateRect(handle, null, true);
  }
}
