class PlaceHold {
  LRESULT WM_LBUTTONDBLCLK(int wParam, int lParam) {
    int oldBits = OS.GetWindowLong(handle, GWL_STYLE);
    int newBits = oldBits & (~OS.WS_TABSTOP);
    OS.SetWindowLong(handle, GWL_STYLE, newBits);
    LRESULT result = super.WM_LBUTTONDBLCLK(wParam, lParam);
    if (isDisposed()) {
      return LRESULT.ZERO;
    }
    OS.SetWindowLong(handle, GWL_STYLE, oldBits);
    if (result == LRESULT.ZERO) {
      return result;
    }
    if (!OS.IsWinCE) {
      if (OS.GetCapture() == handle) {
        OS.ReleaseCapture();
      }
      if (!sendMouseEvent(MouseUp, 1, handle, WM_LBUTTONUP, wParam, lParam)) {
        return LRESULT.ZERO;
      }
    }
    return result;
  }
}
