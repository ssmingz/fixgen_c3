class PlaceHold {
  public Point getSize() {
    checkWidget();
    NSRect frame = (window != null) ? window.frame() : view.frame();
    return new Point(((int) (frame.width)), ((int) (frame.height)));
  }
}
