class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    if (ignoreResize) {
      int code = callWindowProc(WM_SIZE, wParam, lParam);
      if (code == 0) {
        return LRESULT.ZERO;
      }
      return new LRESULT(code);
    }
    LRESULT result = super.WM_SIZE(wParam, lParam);
    if (isDisposed()) {
      return result;
    }
    layoutItems();
    return result;
  }
}
