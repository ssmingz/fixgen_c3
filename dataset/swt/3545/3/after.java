class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    int x = OS.GET_X_LPARAM(lParam);
    int y = OS.GET_Y_LPARAM(lParam);
    for (int i = 0; i < itemCount; i++) {
      ExpandItem item = items[i];
      boolean hover = item.isHover(x, y);
      if (hover && (focusItem != item)) {
        focusItem.redraw(true);
        focusItem = item;
        focusItem.redraw(true);
        forceFocus();
        break;
      }
    }
    return result;
  }
}
