class PlaceHold {
  LRESULT WM_DESTROY(int wParam, int lParam) {
    LRESULT result = super.WM_DESTROY(wParam, lParam);
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & OS.WS_CHILD) != 0) {
      releaseParent();
      release(false);
    }
    return result;
  }
}
