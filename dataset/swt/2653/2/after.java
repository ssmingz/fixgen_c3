class PlaceHold {
  LRESULT WM_KILLFOCUS(int wParam, int lParam) {
    LRESULT result = super.WM_KILLFOCUS(wParam, lParam);
    if (focusItem != null) {
      focusItem.redraw(true);
    }
    return result;
  }
}
