class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    NSRect frame = window.frame();
    float y = display.getPrimaryFrame().height - ((int) (frame.y + frame.height));
    return new Rectangle(
        ((int) (frame.x)), ((int) (y)), ((int) (frame.width)), ((int) (frame.height)));
  }
}
