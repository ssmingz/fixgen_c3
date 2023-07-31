class PlaceHold {
  Rectangle getBounds() {
    return new Rectangle(getX(), 0, width, parent.getClientArea().height);
  }
}
