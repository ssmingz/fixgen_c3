class PlaceHold {
  LRESULT WM_VSCROLL(int wParam, int lParam) {
    LRESULT result = super.WM_VSCROLL(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((lParam == 0) && (verticalBar != null)) {
      result = wmScroll(WM_VSCROLL, wParam, lParam);
      verticalBar.wmScrollChild(wParam, lParam);
    }
    return result;
  }
}
