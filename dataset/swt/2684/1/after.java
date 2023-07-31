class PlaceHold {
  public Rectangle map(Control from, Control to, int x, int y, int width, int height) {
    checkDevice();
    if ((from != null) && from.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((to != null) && to.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (from == to) {
      return new Rectangle(x, y, width, height);
    }
    int hwndFrom = (from != null) ? from.handle : 0;
    int hwndTo = (to != null) ? to.handle : 0;
    RECT rect = new RECT();
    rect.left = x;
    rect.top = y;
    rect.right = x + width;
    rect.bottom = y + height;
    OS.MapWindowPoints(hwndFrom, hwndTo, rect, 2);
    return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
  }
}
