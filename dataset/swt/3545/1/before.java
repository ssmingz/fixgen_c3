class PlaceHold {
  LRESULT WM_MOUSEMOVE(int wParam, int lParam) {
    LRESULT result = super.WM_MOUSEMOVE(wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    int x = ((short) (lParam & 0xffff));
    int y = ((short) (lParam >> 16));
    for (int i = 0; i < itemCount; i++) {
      ExpandItem item = items[i];
      boolean hover = item.isHover(x, y);
      if (item.hover != hover) {
        item.hover = hover;
        item.redraw(false);
      }
    }
    return result;
  }
}
