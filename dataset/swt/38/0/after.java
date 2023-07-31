class PlaceHold {
  LRESULT WM_HSCROLL(int wParam, int lParam) {
    LRESULT result = super.WM_HSCROLL(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (((lParam == 0) || (lParam == handle)) && (horizontalBar != null)) {
      return wmScroll(horizontalBar, WM_HSCROLL, wParam, lParam);
    }
    return result;
  }
}
