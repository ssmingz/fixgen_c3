class PlaceHold {
  LRESULT wmRButtonUp(int hwnd, int wParam, int lParam) {
    Display display = this.display;
    LRESULT result = null;
    if (sendMouseEvent(MouseUp, 3, hwnd, WM_RBUTTONUP, wParam, lParam)) {
      result = new LRESULT(callWindowProc(hwnd, WM_RBUTTONUP, wParam, lParam));
    } else {
      OS.DefWindowProc(hwnd, WM_RBUTTONUP, wParam, lParam);
      result = LRESULT.ZERO;
    }
    int mask = (OS.MK_LBUTTON | OS.MK_MBUTTON) | OS.MK_RBUTTON;
    if (display.xMouse) {
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
