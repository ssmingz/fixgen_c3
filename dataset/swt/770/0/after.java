class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    LRESULT result = super.WM_SIZE(wParam, lParam);
    if (OS.IsWinCE) {
      return result;
    }
    OS.InvalidateRect(handle, null, true);
    return result;
  }
}
