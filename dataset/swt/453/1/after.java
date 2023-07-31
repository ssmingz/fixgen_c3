class PlaceHold {
  public Point toDisplay(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    return toDisplay(point.x, point.y);
  }
}
