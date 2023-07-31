class PlaceHold {
  public Point toControl(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    POINT pt = new POINT();
    pt.x = point.x;
    pt.y = point.y;
    OS.ScreenToClient(handle, pt);
    return new Point(pt.x, pt.y);
  }
}
