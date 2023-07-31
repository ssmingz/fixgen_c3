class PlaceHold {
  LRESULT WM_VSCROLL(int wParam, int lParam) {
    LRESULT result = super.WM_VSCROLL(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (((lParam == 0) || (lParam == handle)) && (verticalBar != null)) {
      return wmScroll(verticalBar, WM_VSCROLL, wParam, lParam);
    }
    return result;
  }
}
