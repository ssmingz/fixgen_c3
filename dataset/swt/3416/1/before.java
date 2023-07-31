class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    LRESULT result = super.WM_SIZE(wParam, lParam);
    if (isDisposed()) {
      return result;
    }
    layoutItems();
    return result;
  }
}
