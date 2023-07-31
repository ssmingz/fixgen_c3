class PlaceHold {
  LRESULT WM_LBUTTONUP(int wParam, int lParam) {
    LRESULT result = super.WM_LBUTTONUP(wParam, lParam);
    if (isDisposed()) {
      return LRESULT.ZERO;
    }
    if (doubleClick) {
      postEvent(DefaultSelection);
    }
    doubleClick = false;
    return result;
  }
}
