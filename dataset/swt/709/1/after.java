class PlaceHold {
  LRESULT wmMouseLeave(int hwnd, int wParam, int lParam) {
    if ((!hooks(MouseExit)) && (!filters(MouseExit))) {
      return null;
    }
    int pos = OS.GetMessagePos();
    POINT pt = new POINT();
    pt.x = ((short) (pos & 0xffff));
    pt.y = ((short) (pos >> 16));
    OS.ScreenToClient(hwnd, pt);
    lParam = pt.x | (pt.y << 16);
    if (!sendMouseEvent(MouseExit, 0, hwnd, WM_MOUSELEAVE, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
