class PlaceHold {
  LRESULT wmXButtonDblClk(int hwnd, int wParam, int lParam) {
    LRESULT result = null;
    Display display = this.display;
    display.captureChanged = false;
    int button = (OS.HIWORD(wParam) == OS.XBUTTON1) ? 4 : 5;
    sendMouseEvent(MouseDown, button, hwnd, WM_XBUTTONDOWN, wParam, lParam);
    if (sendMouseEvent(MouseDoubleClick, button, hwnd, WM_XBUTTONDBLCLK, wParam, lParam)) {
      result = new LRESULT(callWindowProc(hwnd, WM_XBUTTONDBLCLK, wParam, lParam));
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
