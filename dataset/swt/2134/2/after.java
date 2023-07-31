class PlaceHold {
  LRESULT wmXButtonDown(int hwnd, int wParam, int lParam) {
    LRESULT result = null;
    Display display = this.display;
    display.captureChanged = false;
    display.xMouse = true;
    int button = (OS.HIWORD(wParam) == OS.XBUTTON1) ? 4 : 5;
    if (sendMouseEvent(MouseDown, button, hwnd, WM_XBUTTONDOWN, wParam, lParam)) {
      result = new LRESULT(callWindowProc(hwnd, WM_XBUTTONDOWN, wParam, lParam));
    } else {
      result = LRESULT.ZERO;
    }
    if ((!display.captureChanged) && (!isDisposed())) {
      if (OS.GetCapture() != hwnd) {
        OS.SetCapture(hwnd);
      }
    }
    return result;
  }
}
