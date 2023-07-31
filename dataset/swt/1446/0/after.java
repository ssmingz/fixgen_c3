class PlaceHold {
  LRESULT WM_CLEAR(int wParam, int lParam) {
    LRESULT result = super.WM_CLEAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    return verify(WM_CLEAR, wParam, lParam);
  }
}
