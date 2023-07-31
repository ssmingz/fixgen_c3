class PlaceHold {
  LRESULT WM_CUT(int wParam, int lParam) {
    LRESULT result = super.WM_CUT(wParam, lParam);
    if (result != null) {
      return result;
    }
    return verify(WM_CUT, wParam, lParam);
  }
}
