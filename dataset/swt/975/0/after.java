class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    return getControlBounds(topHandle());
  }
}
