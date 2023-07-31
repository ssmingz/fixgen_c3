class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    int oldBits = OS.GetWindowLong(handle, GWL_STYLE);
    int newBits = oldBits & (~OS.WS_TABSTOP);
    OS.SetWindowLong(handle, GWL_STYLE, newBits);
    LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
    OS.SetWindowLong(handle, GWL_STYLE, oldBits);
    if (!OS.IsWinCE) {
      sendMouseEvent(MouseUp, 1, handle, WM_LBUTTONUP, wParam, lParam);
      if (OS.GetCapture() == handle) {
        OS.ReleaseCapture();
      }
    }
    return result;
  }
}
