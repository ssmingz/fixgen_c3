class PlaceHold {
  LRESULT WM_ERASEBKGND(int wParam, int lParam) {
    LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
    if (backgroundImage != null) {
      return LRESULT.ONE;
    }
    return result;
  }
}
