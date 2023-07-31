class PlaceHold {
  LRESULT WM_SETFOCUS(int wParam, int lParam) {
    LRESULT result = super.WM_SETFOCUS(wParam, lParam);
    if (focusIndex != (-1)) {
      items[focusIndex].redraw(true);
    }
    return result;
  }
}
