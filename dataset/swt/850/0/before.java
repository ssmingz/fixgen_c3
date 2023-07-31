class PlaceHold {
  LRESULT wmXButtonUp(int hwnd, int wParam, int lParam) {
    LRESULT result = null;
    int button = ((wParam >> 16) == OS.XBUTTON1) ? 4 : 5;
    if (sendMouseEvent(MouseUp, button, hwnd, WM_XBUTTONUP, wParam, lParam)) {
      result = new LRESULT(callWindowProc(hwnd, WM_XBUTTONUP, wParam, lParam));
    } else {
      result = LRESULT.ZERO;
    }
    int mask =
        (((OS.MK_LBUTTON | OS.MK_MBUTTON) | OS.MK_RBUTTON) | OS.MK_XBUTTON1) | OS.MK_XBUTTON2;
    if (((wParam & 0xffff) & mask) == 0) {
      if (OS.GetCapture() == hwnd) {
        OS.ReleaseCapture();
      }
    }
    return result;
  }
}
