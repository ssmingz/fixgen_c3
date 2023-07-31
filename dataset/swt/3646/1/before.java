class PlaceHold {
  LRESULT WM_ERASEBKGND(int wParam, int lParam) {
    if ((state & TRANSPARENT) != 0) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        Control control = findThemeControl();
        if (control != null) {
          return LRESULT.ONE;
        }
      }
    }
    return null;
  }
}
