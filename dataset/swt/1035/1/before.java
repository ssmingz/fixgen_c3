class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    int code = callWindowProc(WM_SIZE, wParam, lParam);
    super.WM_SIZE(wParam, lParam);
    if (code == 0) {
      return LRESULT.ZERO;
    }
    return new LRESULT(code);
  }
}
