class PlaceHold {
  LRESULT wmMouseWheel(int hwnd, int wParam, int lParam) {
    if ((!hooks(MouseWheel)) && (!filters(MouseWheel))) {
      return null;
    }
    int delta = wParam >> 16;
    int[] value = new int[1];
    int count;
    int detail;
    OS.SystemParametersInfo(SPI_GETWHEELSCROLLLINES, 0, value, 0);
    if (value[0] == OS.WHEEL_PAGESCROLL) {
      detail = SWT.SCROLL_PAGE;
      count = delta / OS.WHEEL_DELTA;
    } else {
      detail = SWT.SCROLL_LINE;
      count = (value[0] * delta) / OS.WHEEL_DELTA;
    }
    POINT pt = new POINT();
    pt.x = ((short) (lParam & 0xffff));
    pt.y = ((short) (lParam >> 16));
    OS.ScreenToClient(hwnd, pt);
    lParam = pt.x | (pt.y << 16);
    if (!sendMouseEvent(MouseWheel, 0, count, detail, true, hwnd, WM_MOUSEWHEEL, wParam, lParam)) {
      return LRESULT.ZERO;
    }
    return null;
  }
}
