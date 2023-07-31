class PlaceHold {
  Rectangle getBounds() {
    return new Rectangle(x, 0, width, parent.getClientArea().height);
  }
}
