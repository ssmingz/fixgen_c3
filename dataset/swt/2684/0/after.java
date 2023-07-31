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
    Point point = map(from, to, x, y);
    return new Rectangle(point.x, point.y, width, height);
  }
}
