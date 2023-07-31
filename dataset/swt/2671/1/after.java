class PlaceHold {
  LRESULT WM_SETFOCUS(int wParam, int lParam) {
    LRESULT result = super.WM_SETFOCUS(wParam, lParam);
    if (isDisposed()) {
      return result;
    }
    if (savedFocus != this) {
      restoreFocus();
    }
    return result;
  }
}
