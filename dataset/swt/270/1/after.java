class PlaceHold {
  LRESULT wmColorChild(int wParam, int lParam) {
    Control control = null;
    if ((state & TRANSPARENT) != 0) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        control = findThemeControl();
      }
    }
    if (((background == (-1)) && (foreground == (-1))) && (control == null)) {
      return null;
    }
    int forePixel = foreground;
    int backPixel = background;
    if (forePixel == (-1)) {
      forePixel = defaultForeground();
    }
    if (backPixel == (-1)) {
      backPixel = defaultBackground();
    }
    OS.SetTextColor(wParam, forePixel);
    OS.SetBkColor(wParam, backPixel);
    if (control != null) {
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      control.drawThemeBackground(wParam, handle, rect);
      OS.SetBkMode(wParam, TRANSPARENT);
      return new LRESULT(OS.GetStockObject(NULL_BRUSH));
    }
    return new LRESULT(findBrush(backPixel));
  }
}
