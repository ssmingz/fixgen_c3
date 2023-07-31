class PlaceHold {
  public Point getLocation() {
    checkWidget();
    NSRect frame = window.frame();
    double y = display.getPrimaryFrame().height - ((int) (frame.y + frame.height));
    return new Point(((int) (frame.x)), ((int) (y)));
  }
}
