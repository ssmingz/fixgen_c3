class PlaceHold {
  LRESULT WM_KILLFOCUS(int wParam, int lParam) {
    LRESULT result = super.WM_KILLFOCUS(wParam, lParam);
    if (focusIndex != (-1)) {
      items[focusIndex].redraw(true);
    }
    return result;
  }
}
