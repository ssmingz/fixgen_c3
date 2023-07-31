class PlaceHold {
  public Point getSize() {
    checkWidget();
    NSRect frame = (window != null) ? window.frame() : view.frame();
    Point point = new Point(((int) (frame.width)), ((int) (frame.height)));
    float scaleFactor = view.window().userSpaceScaleFactor();
    point.x /= scaleFactor;
    point.y /= scaleFactor;
    return point;
  }
}
