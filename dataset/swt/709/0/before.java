class PlaceHold {
  LRESULT wmMouseHover(int hwnd, int wParam, int lParam) {
    sendMouseEvent(MouseHover, 0, hwnd, WM_MOUSEHOVER, wParam, lParam);
    return null;
  }
}
