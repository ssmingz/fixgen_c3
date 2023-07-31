class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    LRESULT result = super.WM_SIZE(wParam, lParam);
    OS.InvalidateRect(handle, null, true);
    return result;
  }
}
