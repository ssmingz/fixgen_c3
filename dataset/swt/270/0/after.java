class PlaceHold {
  LRESULT WM_ERASEBKGND(int wParam, int lParam) {
    if ((state & TRANSPARENT) != 0) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        Control control = findThemeControl();
        if (control != null) {
          RECT rect = new RECT();
          OS.GetClientRect(handle, rect);
          control.drawThemeBackground(wParam, handle, rect);
          return LRESULT.ONE;
        }
      }
    }
    return null;
  }
}
