class PlaceHold {
  public Point getLocation() {
    checkWidget();
    NSRect frame = window.frame();
    float y = display.getPrimaryFrame().height - ((int) (frame.y + frame.height));
    return new Point(((int) (frame.x)), ((int) (y)));
  }
}
