class PlaceHold {
  LRESULT WM_VSCROLL(int wParam, int lParam) {
    LRESULT result = super.WM_VSCROLL(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((verticalBar != null) && ((lParam == 0) || (lParam == handle))) {
      return wmScroll(verticalBar, (state & CANVAS) != 0, handle, WM_VSCROLL, wParam, lParam);
    }
    return result;
  }
}
