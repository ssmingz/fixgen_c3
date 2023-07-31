class PlaceHold {
  LRESULT wmMouseHover(int hwnd, int wParam, int lParam) {
    if (!sendMouseEvent(MouseHover, 0, hwnd, WM_MOUSEHOVER, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
