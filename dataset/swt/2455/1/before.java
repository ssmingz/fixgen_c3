class PlaceHold {
  LRESULT WM_ERASEBKGND(int wParam, int lParam) {
    LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (COMCTL32_MAJOR < 6) {
      drawBackground(wParam);
    }
    return null;
  }
}
