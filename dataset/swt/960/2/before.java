class PlaceHold {
  LRESULT wmLButtonUp(int hwnd, int wParam, int lParam) {
    LRESULT result = null;
    if (sendMouseEvent(MouseUp, 1, hwnd, WM_LBUTTONUP, wParam, lParam)) {
      result = new LRESULT(callWindowProc(hwnd, WM_LBUTTONUP, wParam, lParam));
    } else {
      result = LRESULT.ZERO;
    }
    int mask = (OS.MK_LBUTTON | OS.MK_MBUTTON) | OS.MK_RBUTTON;
    if (OS.GetSystemMetrics(SM_CMOUSEBUTTONS) > 3) {
      mask |= OS.MK_XBUTTON1 | OS.MK_XBUTTON2;
    }
    if (((wParam & 0xffff) & mask) == 0) {
      if (OS.GetCapture() == hwnd) {
        OS.ReleaseCapture();
      }
    }
    return result;
  }
}
