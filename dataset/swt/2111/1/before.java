class PlaceHold {
  LRESULT WM_MOVE(int wParam, int lParam) {
    sendEvent(Move);
    return null;
  }
}
