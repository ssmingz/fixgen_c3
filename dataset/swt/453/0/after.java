class PlaceHold {
  public Point toControl(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    return toControl(point.x, point.y);
  }
}
