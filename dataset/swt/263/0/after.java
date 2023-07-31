class PlaceHold {
  LRESULT WM_HSCROLL(int wParam, int lParam) {
    LRESULT result = super.WM_HSCROLL(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((horizontalBar != null) && ((lParam == 0) || (lParam == handle))) {
      return wmScroll(horizontalBar, (state & CANVAS) != 0, handle, WM_HSCROLL, wParam, lParam);
    }
    return result;
  }
}
